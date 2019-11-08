package ThreadProblem;

import java.util.concurrent.ThreadFactory;

/**
 * @author xiaobaobao
 * @date 2019/6/20 16:22
 */
public class threadFactory implements ThreadFactory {
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
}
