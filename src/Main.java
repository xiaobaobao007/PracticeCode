import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import Util.BufferUtil;
import Util.RandomUtil;
import io.netty.buffer.ByteBufUtil;
import org.junit.Test;

public class Main {

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

	class A {
		public void s() {
			System.out.println("1");
		}
	}

	class B extends A {
		public void s() {
			super.s();
			System.out.println("2");
		}
	}

	@Test
	public void test4() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		for (Integer integer : list) {
			if (integer > 5) {
				list.remove(new Integer(5));
				break;
			}
		}
		System.out.println(list);
	}

	class BMY {
		private int a = 10;
		private String s = "1332";

		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public String getS() {
			return s;
		}

		public void setS(String s) {
			this.s = s;
		}
	}

	@Test
	public void test6() {
		BMY bmy = new BMY();
		for (SerializerFeature value : SerializerFeature.values()) {
			System.out.println(JSON.toJSONString(bmy, value));
		}
	}

	@Test
	public void test5() {
		CyclicBarrier cb = new CyclicBarrier(10);
		new Thread(() -> {
			try {
				cb.await();
			} catch (BrokenBarrierException e) {
				System.out.println("被断开");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(cb.getNumberWaiting());
		cb.reset();
		System.out.println(cb.getNumberWaiting());
	}

	@Test
	public void test7() {
		CyclicBarrier cb = new CyclicBarrier(2);
		try {
			cb.await(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	private enum TransForm {
		;
		public int type;

		TransForm(int type) {
			this.type = type;
		}
	}

	public static void main(String[] args) {
		CountDownLatch count = new CountDownLatch(1);

		new Thread(() -> {
			try {
				// TimeUnit.SECONDS.sleep(1);
				count.await();
				System.out.println("OVER");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				count.countDown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

	}


}