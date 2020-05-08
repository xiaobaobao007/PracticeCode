package NIO.PB.Serialization;

import NIO.PB.HelloWorld.Helloworld;
import NIO.PB.HelloWorld.Helloworld.HelloRequest;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import com.google.protobuf.InvalidProtocolBufferException;
import com.googlecode.protobuf.format.JsonFormat;

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

	@Test
	public void json_test() throws Exception {
		JsonFormat jsonFormat = new JsonFormat();
		Helloworld.allValue.Builder builder = Helloworld.allValue.newBuilder();
		builder.setInt32Value(123);
		builder.setFloatValue(1.2F);
		builder.setStringValue("sdfe");
		String json = jsonFormat.printToString(builder.build());
		System.out.println(json);

		Helloworld.allValue.Builder newBuilder = Helloworld.allValue.newBuilder();
		jsonFormat.merge(new ByteArrayInputStream(json.getBytes()), newBuilder);
		System.out.println(newBuilder.getInt32Value());
		System.out.println(newBuilder.getFloatValue());
		System.out.println(newBuilder.getStringValue());
	}
}
