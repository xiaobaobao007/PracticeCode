package NIO.Netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class NettyServerHandler extends SimpleChannelInboundHandler {

	private int counter = 0;
	private NettyServer nettyServer;

	public NettyServerHandler(NettyServer nettyServer) {
		this.nettyServer = nettyServer;
	}

	/**
	 * 当读到消息
	 *
	 * @param ctx
	 * @param o
	 * @throws Exception
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
		System.out.println("client say：" + o.toString());
		String clientName = ctx.channel().remoteAddress().toString();
		Channel channel = nettyServer.getChannelMap().get(clientName);
		channel.writeAndFlush("1" + System.getProperty("line.separator"));
		ctx.writeAndFlush("2");
		ctx.channel().writeAndFlush("3");
		ctx.flush();
		counter = 0;
	}

	/**
	 * 当建立了连接
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String clientName = ctx.channel().remoteAddress().toString();
		System.out.println("RemoteAddress:" + clientName + "active!");
		nettyServer.setClient(clientName);
		nettyServer.setChannel(clientName, ctx.channel());
		super.channelActive(ctx);
		counter = 0;
	}

	/**
	 * 每当从服务端收到新的客户端连接时
	 *
	 * @param ctx
	 * @throws Exception
	 */
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
		System.out.println("收到新的客户端连接：" + ctx.name());
	}

	/**
	 * 每当从服务端收到客户端断开时
	 *
	 * @param ctx
	 * @throws Exception
	 */
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);
		System.out.println("客户端断开");
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state().equals(IdleState.READER_IDLE)) {
				//空闲4s后触发
				if (counter >= 10) {
					ctx.channel().close().sync();
					String clientName = ctx.channel().remoteAddress().toString();
					System.out.println("" + clientName + "offline");
					nettyServer.removeClient(clientName);
					//判断是否有在线的
					if (nettyServer.getClientMapSize()) {
						return;
					}
				} else {
					counter++;
					System.out.println("loss" + counter + "count HB");
				}
			}
		}
	}
}