import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.alibaba.fastjson.JSONObject;

import org.junit.Test;

public class Main {

	@Test
	public void test1() {
		One one = new One();
		one.setServerId(123);
		one.setUserId(456);
		one.setBmy(789);

		Class<One> clazz = One.class;
		One parse = JSONObject.parseObject(JSONObject.toJSONString(one), clazz);
		System.out.println(parse.toString());
	}

	@Test
	public void test2() {
		JSONObject json = new JSONObject();
		json.put("1", "123");
		String s = json.toJSONString();
		System.out.println(s);
		JSONObject object = JSONObject.parseObject(s);
		System.out.println(object.toJSONString());
	}

	@Test
	public void test3() {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1, 123);
		map.computeIfPresent(2, (K, V) -> {
			System.out.println(">>>>" + V);
			return V;
		});
		map.computeIfPresent(1, (K, V) -> {
			System.out.println(">>>>" + V);
			return V;
		});
	}

	@Test
	public void test4() {
		Thread thread = new Thread(()->{
			for (int i=0; ;i++ ) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(i);
			}
		});
		// thread.setDaemon(true);
		thread.start();

		System.out.println("YES");
	}

}