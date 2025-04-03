package per.bmy.NIO.Learn;

import java.nio.ByteBuffer;

/**
 * 英文
 * 字节数 : 1; 编码：GB2312
 * 字节数 : 1; 编码：GBK
 * 字节数 : 1; 编码：GB18030
 * 字节数 : 1; 编码：ISO-8859-1
 * 字节数 : 1; 编码：UTF-8
 * 字节数 : 4; 编码：UTF-16
 * 字节数 : 2; 编码：UTF-16BE
 * 字节数 : 2; 编码：UTF-16LE
 * <p>
 * 中文
 * 字节数 : 2; 编码：GB2312
 * 字节数 : 2; 编码：GBK
 * 字节数 : 2; 编码：GB18030
 * 字节数 : 1; 编码：ISO-8859-1
 * 字节数 : 3; 编码：UTF-8
 * 字节数 : 4; 编码：UTF-16
 * 字节数 : 2; 编码：UTF-16BE
 * 字节数 : 2; 编码：UTF-16LE
 *
 * @author xiaobaobao
 * @date 2020/4/26，13:37
 */
public class ByteBufLearn {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.limit(15);
        buffer.put("123456我".getBytes());
        System.out.println(String.format("%d,%d,%d", buffer.position(), buffer.limit(), buffer.capacity()));
        buffer.flip();
        System.out.println(String.format("%d,%d,%d", buffer.position(), buffer.limit(), buffer.capacity()));

        byte[] readByte = new byte[10];
        buffer.get(readByte);
        System.out.println(String.format("%d,%d,%d", buffer.position(), buffer.limit(), buffer.capacity()));
        System.out.println(new String(readByte));
    }
}