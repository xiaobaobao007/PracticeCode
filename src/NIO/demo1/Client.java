package NIO.demo1;

import Util.RandomUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author bao meng yang <932824098@qq.com>
 * @date 2021/2/20，10:25:09
 */
public class Client {
	public static void main(String[] args) {
		NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap()
				                      .group(nioEventLoopGroup)
				                      .option(ChannelOption.SO_KEEPALIVE, true)
				                      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				                      .channel(NioSocketChannel.class)
				                      .handler(new ChannelInitializer<NioSocketChannel>() {
					                      @Override
					                      protected void initChannel(NioSocketChannel ch) throws Exception {
						                      ch.pipeline().addLast(
								                      new ChannelInboundHandlerAdapter() {
									                      @Override
									                      public void channelActive(ChannelHandlerContext ctx) throws Exception {
										                      for (int i = 0; i < 1000; i++) {

											                      ByteBuf send = Unpooled.buffer();
											                      int num = RandomUtil.nextInt(5) + 1;
											                      send.writeInt(num + 2 << 1);
											                      send.writeChar('>');
											                      for (int j = num; j > 0; j--) {
												                      send.writeChar('.');
											                      }
											                      send.writeChar('<');

											                      ByteBuf buffer = ctx.alloc().buffer();
											                      buffer.writeBytes(send);
											                      // BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(send);
											                      // System.out.println(binaryWebSocketFrame);
											                      // ctx.channel().writeAndFlush(binaryWebSocketFrame);
											                      ctx.channel().writeAndFlush(buffer);
										                      }
									                      }
									                      @Override
									                      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
									                      }
								                      }
						                      );
					                      }
				                      });
		bootstrap.connect("localhost", 8080);
	}
}
