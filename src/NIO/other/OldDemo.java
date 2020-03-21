package NIO.other;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xiaobaobao
 * @date 2020/3/18，14:17
 */
public class OldDemo {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8088);
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("新的科幻段连接");
			handel(socket);
		}
	}

	static void handel(Socket socket) throws IOException {
		byte[] bytes = new byte[1080];
		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();
		DataOutputStream out = new DataOutputStream(outputStream);
		while (true) {
			int read = inputStream.read(bytes);
			if (read > 0) {
				System.out.println(new String(bytes, 0, read));
				out.writeUTF("hi" + System.currentTimeMillis() % 10);
			} else {
				System.out.println("断开");
				break;
			}
		}
	}
}
