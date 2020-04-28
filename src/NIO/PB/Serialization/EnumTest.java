package NIO.PB.Serialization;

import NIO.PB.HelloWorld.Helloworld;

import java.util.Arrays;

/**
 * @author xiaobaobao
 * @date 2020/4/28，20:49
 */
public class EnumTest {
	public static void main(String[] args) {
		Helloworld.allValue.Builder builder = Helloworld.allValue.newBuilder();
		builder.setInt32Value(32);
		System.out.println(Arrays.toString(builder.build().toByteArray()));
	}
}
