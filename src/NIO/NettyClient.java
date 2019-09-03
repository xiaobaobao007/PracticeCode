package NIO;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @author xiaobaobao
 * @date 2019/9/1 18:11
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        //创建一个通道的辅助
        Bootstrap bootstrap = new Bootstrap();
        //线程池，用于发送请求信息
        NioEventLoopGroup group = new NioEventLoopGroup();
        //绑定线程池
        bootstrap.group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
//                .handler(new DefaultClientChannelInitializer(nioHandler));
                //绑定NIO模型
                .channel(NioSocketChannel.class)
                //监听通道的动作
                .handler(new SimpleChannelInboundHandler<String>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                        System.out.println("服务器发送：" + msg);
                    }
                });
        //创建一个通道
        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();
        while (true) {
            try {
                Thread.sleep(1000);
                channel.writeAndFlush(new Date());
            } catch (Exception e) {
                System.out.println("没有消息");
            }
        }
    }
}