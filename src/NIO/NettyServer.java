package NIO;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

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
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,
                                Unpooled.copiedBuffer(System.getProperty("line.separator").getBytes())));
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                                ctx.writeAndFlush("im fuwuqi" + System.getProperty("line.separator"));
                            }
                        });
                    }
                });
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