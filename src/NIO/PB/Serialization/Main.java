package NIO.PB.Serialization;

import NIO.PB.HelloWorld.Helloworld;
import NIO.PB.HelloWorld.Helloworld.HelloRequest;
import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author xiaobaobao
 * @date 2020/3/22，15:08
 */
public class Main {

	@Test
	public void builder_test() throws InvalidProtocolBufferException {
		HelloRequest.Builder builder = HelloRequest.newBuilder();
		builder.setName("你好");
		HelloRequest build = builder.build();
		build = build.toBuilder().setName("修改后").build();
		byte[] bytes = build.toByteArray();
		HelloRequest helloRequest = HelloRequest.parseFrom(bytes);
		System.out.println(helloRequest.getName());
	}

	@Test
	public void enum_test() {
		Helloworld.allValue.Builder builder = Helloworld.allValue.newBuilder();
		builder.setEnumValue(Helloworld.allValue.EnumValue.c);
		System.out.println(builder.getEnumValue());
		System.out.println(builder.getEnumValueValue());
	}

	@Test
	public void oneof_test() {
		Helloworld.allValue.Builder builder = Helloworld.allValue.newBuilder();
		builder.setOne1("1");

		builder.setOne2("2");
		System.out.println(builder.getOne1() + "|" + builder.getOne2());

		builder.setOne1("1");
		System.out.println(builder.getOne1() + "|" + builder.getOne2());
	}
}
