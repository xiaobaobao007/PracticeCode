package per.bmy.NIO.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author bao meng yang <932824098@qq.com>
 * @date 2021/2/20，10:23:16
 */
public class Server {
    public static void main(String[] args) {

        AtomicLong count = new AtomicLong(0);
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker);

        serverBootstrap.channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LengthFieldBasedFrameDecoder(
                                        Integer.MAX_VALUE,  // maxFrameLength
                                        0,     // lengthFieldOffset
                                        4,     // lengthFieldLength
                                        0,     // lengthAdjustment
                                        4))     // initalBytesToStrip 跳过2字节，也就是跳过长度域)
                                // .addLast(new LengthFieldBasedFrameDecoder(
                                // 		ByteOrder.LITTLE_ENDIAN,
                                // 		Integer.MAX_VALUE,  // maxFrameLength
                                // 		0,     // lengthFieldOffset
                                // 		4,     // lengthFieldLength
                                // 		0,     // lengthAdjustment
                                // 		4,// initalBytesToStrip 跳过2字节，也就是跳过长度域)
                                // 		true))
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                        ByteBuf byteBuf = (ByteBuf) msg;
                                        long l = count.incrementAndGet();
                                        System.out.println(l + ": " + byteBuf.toString(StandardCharsets.UTF_8));
                                    }

                                    @Override
                                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                        count.set(0L);
                                        System.out.println();
                                    }
                                });
                    }
                }).bind(8080);
    }
}
