package per.bmy.NIO.Netty.HttpDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class HttpServer {

    public static final int port = 8080; //设置服务端端口
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static ServerBootstrap b = new ServerBootstrap();
    private static final boolean SSL = false;

    public static void main(String[] args) throws Exception {
        final SslContext sslCtx;
        if (SSL) {
            //netty为我们提供的ssl加密，缺省
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(),
                    ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }
        try {
            b.group(group);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ServerHandlerInit(sslCtx));
            // 服务器绑定端口监听
            ChannelFuture f = b.bind(port).sync();
            System.out.println("服务端启动成功,端口是:" + port);
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();
            System.out.println("关闭");
        } finally {
            group.shutdownGracefully();
        }
    }
}