package NIO.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @author xiaobaobao
 * @date 2019/9/1 18:11
 */
public class Client {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							ChannelPipeline pipeline = socketChannel.pipeline();
							pipeline.addLast("LengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
							pipeline.addLast("LengthFieldPrepender", new LengthFieldPrepender(4));
							pipeline.addLast("StringDecoder", new StringDecoder(CharsetUtil.UTF_8));
							pipeline.addLast("StringEncoder", new StringEncoder(CharsetUtil.UTF_8));
							pipeline.addLast("SocketServerHandler", new SimpleChannelInboundHandler<String>() {
								@Override
								protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
									System.out.println("client output:" + msg);
									ctx.writeAndFlush("from client:" + UUID.randomUUID());
								}

								@Override
								public void channelActive(ChannelHandlerContext ctx) throws Exception {
									ctx.writeAndFlush("from client: hello world");
								}

								@Override
								public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
									cause.printStackTrace();
									ctx.close();
								}
							});
						}
					});
			ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}