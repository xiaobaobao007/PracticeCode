package NIO.PB.Serialization;

import NIO.PB.HelloWorld.Helloworld;
import NIO.PB.HelloWorld.Helloworld.HelloRequest;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	@Test
	public void map_test() {
		Helloworld.allValue.Builder builder = Helloworld.allValue.newBuilder();
		builder.putInt32Map(1, 2);
		System.out.println(builder.getInt32MapMap());
		builder.putInt32Map(1, 3);
		System.out.println(builder.getInt32MapMap());
		builder.putInt32Map(2, 3);
		System.out.println(builder.getInt32MapMap());
	}

	@Test
	public void sint() {
		Helloworld.allValue.Builder a = Helloworld.allValue.newBuilder();
		a.setSint32Value(0);
		System.out.println(Arrays.toString(a.build().toByteArray()));

		Helloworld.allValue.Builder b = Helloworld.allValue.newBuilder();
		b.setSint32Value(1);
		System.out.println(Arrays.toString(b.build().toByteArray()));

		Helloworld.allValue.Builder c = Helloworld.allValue.newBuilder();
		c.setSint32Value(-1);
		System.out.println(Arrays.toString(c.build().toByteArray()));
	}

	@Test
	public void oneof() {
		Helloworld.allValue.Builder a = Helloworld.allValue.newBuilder();
		System.out.println(a.getTestOneofCase());
		a.setOne1("111");
		System.out.println(a.getTestOneofCase());
		a.setOne2("222");
		System.out.println(a.getTestOneofCase());
	}

	@Test
	public void size() {
		Helloworld.allValue.Builder a = Helloworld.allValue.newBuilder();
		a.addIntList(1).build();
	}
}
