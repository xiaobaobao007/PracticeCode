package NIO.WebSocket.Client;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.Message;
import com.intion.app.packets.Common;
import com.intion.app.packets.ProtocolKey;
import com.intion.app.packets.User;

import org.java_websocket.WebSocket;

public class Client {

	private final int id;
	private WsClient myClient;
	protected CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

	protected User.loginBack loginBack;

	public Client(int id) {
		this.id = id;
	}

	public void init(URI serverUri) {
		myClient = new WsClient(serverUri, id, this);
		myClient.connect();
		while (!myClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		try {
			login();
			levelUpHero();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void levelUpHero() {
		for (Common.HeroVO heroVO : loginBack.getHeroVOList()) {
			User.heroLevelUp.Builder heroLevelUp = User.heroLevelUp.newBuilder();
			heroLevelUp.setType(1);
			heroLevelUp.setHeroId(heroVO.getHid());
			for (int i = 0; i < 10; i++) {
				send(ProtocolKey.HeroLevelUp, heroLevelUp.build());
			}
		}
	}

	private void login() throws BrokenBarrierException, InterruptedException {
		User.login.Builder builder = User.login.newBuilder();
		builder.setAccount("TEST " + id);
		send(ProtocolKey.Login, builder.build());
		cyclicBarrier.await();
	}

	private void send(short cmd, Message message) {
		byte[] messageByte = message.toByteArray();
		ByteBuffer byteBuf = ByteBuffer.allocate(messageByte.length + 10);
		byteBuf.putShort(cmd);
		byteBuf.putInt(0);
		byteBuf.putInt(messageByte.length);
		byteBuf.put(messageByte);
		myClient.send(byteBuf.array());
	}

}
