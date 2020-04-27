package NIO.Learn;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

import java.util.concurrent.Callable;

/**
 * Created by arthur.dy.lee on 2019-10-20.
 */
public class NettyFutureDemo {

	public static void main(String[] args) throws InterruptedException {
		long l = System.currentTimeMillis();
		System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
		EventExecutorGroup group = new DefaultEventExecutorGroup(4);//创建执行线程池SingleThreadEventExecutor
		System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
		Future<Integer> f = group.submit(new Callable<Integer>() { //创建Callable
			@Override
			public Integer call() throws Exception {
				System.out.println("执行耗时操作...");
				timeConsumingOperation();
				return 100;
			}
		});
		System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
		//添加匿名监听者
		f.addListener((FutureListener<Object>) objectFuture -> {
			System.out.println("计算结果:：" + objectFuture.get()); //promise.get获取集果
		});
		//也可以简写如下
//		f.addListener((FutureListener) future -> System.out.println("计算结果2:：" + future.get()));

		System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
//		new CountDownLatch(1).await();
	}

	static void timeConsumingOperation() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
