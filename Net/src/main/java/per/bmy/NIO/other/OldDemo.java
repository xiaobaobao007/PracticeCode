package per.bmy.NIO.other;


import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/3/18，14:17
 */
public class OldDemo {

    @Test
    public void oneThread() throws Exception {
        ServerSocket serverSocket = new ServerSocket(8088);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("新的客户端连接");
            handel(socket);
        }
    }

    @Test
    public void multiThread() throws Exception {
        ServerSocket serverSocket = new ServerSocket(8088);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("新的客户端连接");
            new Thread(() -> handel(socket)).start();
        }
    }


    static void handel(Socket socket) {
        try {
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
        } catch (Exception e) {
            System.out.println("连接建立异常");
            e.printStackTrace();
        }

    }
}
