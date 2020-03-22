package NIO.PB.Serialization;

import NIO.PB.HelloWorld.Helloworld.HelloRequest;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author xiaobaobao
 * @date 2020/3/22，15:08
 */
public class Main {
	public static void main(String[] args) throws InvalidProtocolBufferException {
		HelloRequest.Builder builder = HelloRequest.newBuilder();
		builder.setName("你好");
		HelloRequest build = builder.build();
		byte[] bytes = build.toByteArray();
		HelloRequest helloRequest = HelloRequest.parseFrom(bytes);
		System.out.println(helloRequest.getName());
	}
}
