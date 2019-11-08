package NIO.Netty;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyServer {

	private int port = 20803;
	//维护设备在线的表
	private Map<String, Integer> clientMap = new HashMap<>();

	public synchronized void setClient(String name) {
		this.clientMap.put(name, 1);
	}

	public synchronized void removeClient(String name) {
		this.clientMap.remove(name);
	}

	//判断是否还有连接
	public synchronized boolean getClientMapSize() {
		return this.clientMap.size() > 0;
	}

	//维护设备连接的map 用于推送消息
	private Map<String, Channel> channelMap = new HashMap<>();

	public synchronized void setChannel(String name, Channel channel) {
		this.channelMap.put(name, channel);
	}

	public synchronized Map<String, Channel> getChannelMap() {
		return this.channelMap;
	}

	//发送消息给下游设备
	public boolean writeMsg(String msg) {
		//初始状态正常
		boolean normal = true;
		Map<String, Channel> channelMap = getChannelMap();
		if (channelMap.size() == 0) {
			return true;
		}
		Set<String> keySet = clientMap.keySet();
		for (String key : keySet) {
			try {
				Channel channel = channelMap.get(key);
				if (!channel.isActive()) {
					normal = false;
					continue;
				}
				//System.getProperty("line.separator") 换行符的作用，在windows和linux一样的效果
				channel.writeAndFlush(msg + System.getProperty("line.separator"));
			} catch (Exception e) {
				normal = false;
			}
		}
		return normal;
	}

	public void bind() {
		System.out.println("service start successful");
		ServerBootstrap bootstrap = new ServerBootstrap();
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		bootstrap.group(bossGroup, workerGroup)
				//设置协议（这个协议很常用，异步的TCP协议）
				.channel(NioServerSocketChannel.class)
				//设置长连接
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				//指定待处理的消息队列的大小
				.option(ChannelOption.SO_BACKLOG, 1024)
//                //当服务器成功启动会初始化执行（监听channel的变化）
//                .handler(new LoggingHandler(LogLevel.INFO))
				//当客户端连接到服务器的初始化（监听连接成功的channel的变化）
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) {
						//将Channel数据抽象为ChannelPipeline
						ChannelPipeline pipeline = socketChannel.pipeline();
						//定义消息的最大长度的结束符
						pipeline.addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,
								Unpooled.copiedBuffer(System.getProperty("line.separator").getBytes())));
						//编码格式
						pipeline.addLast("decoder", new StringDecoder());
						//解码格式
						pipeline.addLast("encoder", new StringEncoder());
						//心跳机制
						pipeline.addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS));
						//服务器逻辑执行
						pipeline.addLast("handler", new NettyServerHandler(NettyServer.this));
					}
				});

		try {
			ChannelFuture f = bootstrap.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		final NettyServer nettyServer = new NettyServer();
		new Thread(nettyServer::bind).start();
		Scanner scanner = new Scanner(System.in);
		String msg;
		while (!(msg = scanner.nextLine()).equals("exit")) {
			//检测发送消息是否成功
			System.out.println("Send State：" + nettyServer.writeMsg(msg));
		}
	}

}