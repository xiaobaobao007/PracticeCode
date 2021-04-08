package NIO.WebSocket.Client;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author bao meng yang <932824098@qq.com>
 * @date 2021/4/8，13:59:11
 */
public class Main {

	private static final String SERVER_URL = "ws://localhost:9800/";

	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI(SERVER_URL);
		for (int i = 0; i < 1; i++) {
			int finalI = i;
			new Thread(() -> {
				Client client = new Client(finalI);
				client.init(uri);
				client.start();
			}).start();
		}
	}

}
