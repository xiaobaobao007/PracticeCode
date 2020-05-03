package basis;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * -Xmx2m -Xms2m
 *
 * @author xiaobaobao
 * @date 2020/5/3，19:24
 */
public class ReferenceTest {

	/**
	 * 强引用：永远不会被清理
	 */
	@Test
	public void FinalReferenceTest() {
		/**
		 * 可达性分析
		 *
		 *
		 *
		 */
	}

	/**
	 * 软引用：内存不够的时候就会被清理
	 */
	@Test
	public void SoftReferenceTest() {
		System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓软引用↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
		List<SoftReference> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			byte[] buff = new byte[1024 * 300];
			SoftReference<byte[]> sr = new SoftReference<>(buff);
			list.add(sr);
		}
		System.gc();
		int aliveNums = 0;
		for (SoftReference softReference : list) {
			aliveNums++;
			if (softReference.get() != null) {
				System.out.println(aliveNums);//只会输出10
			}
		}
	}

	/**
	 * 弱引用：只要GC就会清除
	 */
	@Test
	public void WeakReferenceTest() {
		System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓弱引用↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
		MainTest obj = new MainTest();
		WeakReference sf = new WeakReference(obj);
		System.out.println("是否被回收" + sf.get());
		obj = null;
		System.out.println("是否被回收" + sf.get());
		System.gc();
		System.out.println("是否被回收" + sf.get());
	}

	@Test
	public void PhantomReference() {
		System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓虚引用↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
		MainTest obj = new MainTest();
		ReferenceQueue referenceQueue = new ReferenceQueue<>();
		PhantomReference sf = new PhantomReference<>(obj, referenceQueue);
		System.out.println(referenceQueue.poll());
		System.out.println("是否被回收" + sf.get());
		obj = null;
		System.out.println(referenceQueue.poll());
		System.out.println("是否被回收" + sf.get());
		System.gc();
		System.out.println(referenceQueue.poll());
		System.out.println("是否被回收" + sf.get());
	}
}
