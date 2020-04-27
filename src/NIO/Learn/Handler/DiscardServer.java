package NIO.Learn.Handler;

/**
 * ByteBufAllocator			 ALLOCATOR
 * RecvByteBufAllocator		 RCVBUF_ALLOCATOR
 * MessageSizeEstimator		 MESSAGE_SIZE_ESTIMATOR
 * Integer					 CONNECT_TIMEOUT_MILLIS
 * Integer					 MAX_MESSAGES_PER_READ
 * <p>
 * Integer					 WRITE_SPIN_COUNT
 * Integer					 WRITE_BUFFER_HIGH_WATER_MARK
 * Integer					 WRITE_BUFFER_LOW_WATER_MARK
 * WriteBufferWaterMark		 WRITE_BUFFER_WATER_MARK
 * Boolean					 ALLOW_HALF_CLOSURE
 * <p>
 * Boolean					 AUTO_READ
 * Boolean					 AUTO_CLOSE
 * <p>
 * Boolean					 SO_BROADCAST
 * Boolean					 SO_KEEPALIVE
 * Integer					 SO_SNDBUF
 * Integer					 SO_RCVBUF
 * Boolean					 SO_REUSEADDR
 * Integer					 SO_LINGER
 * Integer					 SO_BACKLOG 三次握手的请求是顺序处理，决定请求的队列长度。
 * Integer					 SO_TIMEOUT
 * Integer					 IP_TOS
 * <p>
 * InetAddress				 IP_MULTICAST_ADDR
 * NetworkInterface			 IP_MULTICAST_IF
 * Integer					 IP_MULTICAST_TTL
 * Boolean					 IP_MULTICAST_LOOP_DISABLED
 * Boolean					 TCP_NODELAY
 * Boolean					 DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION
 * Boolean					 SINGLE_EVENTEXECUTOR_PER_GROUP
 *
 * @author xiaobaobao
 * @date 2020/4/26，17:19
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 丢弃任何进入的数据
 */
public class DiscardServer {
	private int port;

	public DiscardServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		//用来接受进来的连接
		EventLoopGroup bossGroup = new NioEventLoopGroup(8); // (1)
		//用来处理已经接受的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup(16);
		try {
			//是一个NIO服务的辅助启动类
			ServerBootstrap b = new ServerBootstrap(); // (2)
			b.group(bossGroup, workerGroup)
					//如何接受一个新进来的连接
					.channel(NioServerSocketChannel.class) // (3)
					.option(ChannelOption.SO_BACKLOG, 128) // (5)

					.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new DiscardServerHandler());
						}
					})
					.childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

			ChannelFuture channelFuture = b.bind(port).sync(); // (7)
			System.out.println(DiscardServer.class.getName() + " started and listening for connections on " + channelFuture.channel().localAddress());
			channelFuture.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new DiscardServer(port).run();
	}
}