package per.bmy.NIO.Netty.HttpDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

public class ServerHandlerInit extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public ServerHandlerInit(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        if (sslCtx != null) {
            ph.addLast(sslCtx.newHandler(ch.alloc()));
        }
        //http响应编码
        ph.addLast("encode", new HttpResponseEncoder());
        //http请求编码
        ph.addLast("decode", new HttpRequestDecoder());
        //聚合http请求
        ph.addLast("aggre",
                new HttpObjectAggregator(10 * 1024 * 1024));
        //启用http压缩
        ph.addLast("compressor", new HttpContentCompressor());
        //自己的业务处理
        ph.addLast("busi", new BusiHandler());

    }
}