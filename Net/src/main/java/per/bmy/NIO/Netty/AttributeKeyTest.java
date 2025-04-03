package per.bmy.NIO.Netty;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @author xiaobaobao
 * @date 2020/4/27，20:16
 * <p>
 * {@link io.netty.util.AttributeMap} 源码需要再了解
 */
public class AttributeKeyTest {


    public static void main(String[] args) {

        AttributeKey<String> SERVER_TYPE = AttributeKey.valueOf("serverType");

        Channel channel = new NioServerSocketChannel();

        System.out.println(channel.hasAttr(SERVER_TYPE));
        System.out.println(channel.attr(SERVER_TYPE).get());
        System.out.println(channel.attr(SERVER_TYPE).get());

        channel.attr(SERVER_TYPE).set("123");

        System.out.println(channel.hasAttr(SERVER_TYPE));
        System.out.println(channel.attr(SERVER_TYPE).get());

    }
}
