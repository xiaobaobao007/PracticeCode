package per.bmy.NIO.Learn.Handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.StandardCharsets;

/**
 * @author xiaobaobao
 * @date 2020/4/26，17:01
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//		ByteBuf in = (ByteBuf) msg;
//		try {
//			System.out.println(in.toString(CharsetUtil.US_ASCII));
////			while (in.isReadable()) {
////				System.out.print((char) in.readByte());
////				System.out.flush();
////			}
//		} finally {
//			ReferenceCountUtil.release(msg);
////			in.release();
//		}
//		ctx.writeAndFlush(msg);

        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8);
        System.err.println(body);
        String reqString = "Hello I am Server";
        ByteBuf resp = Unpooled.copiedBuffer(reqString.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println("退出");
    }

}
