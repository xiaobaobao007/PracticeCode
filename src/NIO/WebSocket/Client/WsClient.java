package NIO.WebSocket.Client;

import java.net.URI;
import java.nio.ByteBuffer;

import com.intion.app.packets.ProtocolKey;
import com.intion.app.packets.User;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WsClient extends WebSocketClient {

	private final int id;
	private final Client client;

	public WsClient(URI serverUri, int id, Client client) {
		super(serverUri);
		this.id = id;
		this.client = client;
	}

	@Override
	public void onMessage(ByteBuffer byteBuffer) {
		short cmd = byteBuffer.getShort();

		ProtocolKey.MessageAndId messageAndId = ProtocolKey.messageMap.get(cmd);

		if (messageAndId == null) {
			System.out.println("不支持的协议");
		} else {
			@SuppressWarnings("unused")
			int sendTime = byteBuffer.getInt();
			short result = byteBuffer.getShort();
			System.out.println(messageAndId.key + " " + result);
			if (result == 1) {
				int msgLength = byteBuffer.getInt();

				byte[] data = null;
				if (msgLength != 0) {
					data = new byte[msgLength];
					byteBuffer.get(data);
				}

				if (data != null) {
					try {
						switch (cmd) {
							case ProtocolKey.Login:
								client.loginBack = User.loginBack.parseFrom(data);
								client.cyclicBarrier.await();
								break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void onOpen(ServerHandshake arg0) {
		System.out.println(id + " 握手成功");

	}

	@Override
	public void onMessage(String s) {
	}

	@Override
	public void onClose(int arg0, String arg1, boolean arg2) {
		System.out.println("连接关闭");
	}

	@Override
	public void onError(Exception e) {
		e.printStackTrace();
		System.out.println("发生错误");
	}
}
