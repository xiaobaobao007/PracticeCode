package NIO;


import NIO.PB.HelloWorld.Helloworld;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaobaobao
 * @date 2019/9/1 18:08
 */
public class NettyServer {
	public static void main(String[] args) {
		//创建一个通道
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		//线程池，用来监听客户端的连接请求
		NioEventLoopGroup boos = new NioEventLoopGroup();
		//线程池，用来进行业务逻辑处理
		NioEventLoopGroup worker = new NioEventLoopGroup();
		serverBootstrap
				.group(boos, worker)
				.channel(NioServerSocketChannel.class)

				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					protected void initChannel(NioSocketChannel ch) {
						ChannelPipeline p = ch.pipeline();
						p.addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,
								Unpooled.copiedBuffer(System.getProperty("line.separator").getBytes())));

						//TODO netty如何搭载PB
						//一次解码器
						p.addLast(new ProtobufVarint32FrameDecoder());
						//二次解码器
						p.addLast(new ProtobufDecoder(Helloworld.Frame.getDefaultInstance()));
						//一次编码器
						p.addLast(new ProtobufVarint32LengthFieldPrepender());
						//二次编码器
						p.addLast(new ProtobufEncoder());

						//idle操作
						p.addLast("IdleStateHandler", new IdleStateHandler(0, 20, 0, TimeUnit.SECONDS));

						p.addLast(new SimpleChannelInboundHandler<String>() {
							@Override
							protected void channelRead0(ChannelHandlerContext ctx, String msg) {
								System.out.println(msg);
								ctx.writeAndFlush("im fuwuqi" + System.getProperty("line.separator"));
							}
						});
					}
				})
				//keeplive
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childOption(NioChannelOption.SO_KEEPALIVE, true);
		try {
			ChannelFuture future = serverBootstrap.bind(8000).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			boos.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
}