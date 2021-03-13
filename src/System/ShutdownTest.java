package System;

import java.util.concurrent.TimeUnit;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * @author bao meng yang <932824098@qq.com>
 * @date 2021/3/4，15:52:47
 */
public class ShutdownTest {

	public static void main(String[] args) {
		new ShutdownTest().test();
	}

	public ShutdownTest() {
	}

	public void test() {
		Signal sig = new Signal(getOSSignalType());

		Signal.handle(sig, new ShutdownHandler());

		Thread t = new Thread(new ShutdownHook(), "ShutdownHook-Thread");
		Runtime.getRuntime().addShutdownHook(t);
	}

	private String getOSSignalType() {
		return System.getProperties().getProperty("os.name").toLowerCase().startsWith("win") ? "INT" : "USR2";
	}

	static class ShutdownHandler implements SignalHandler {
		@Override
		public void handle(Signal signal) {
			System.out.println("CLOSE");
		}
	}

	static class ShutdownHook implements Runnable {
		@Override
		public void run() {
			System.out.println("ShutdownHook execute start...");
			try {
				TimeUnit.SECONDS.sleep(3);//模拟应用进程退出前的处理操作
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("ShutdownHook execute end...");
		}
	}

}
