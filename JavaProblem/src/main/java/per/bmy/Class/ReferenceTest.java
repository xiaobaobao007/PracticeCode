package per.bmy.Class;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import org.junit.Test;

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
         */
    }

    /**
     * 软引用：内存不够的时候就会被清理
     */
    @Test
    public void SoftReferenceTest() {
        System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓软引用↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            byte[] buff = new byte[1024 * 1024];
            SoftReference<byte[]> sr = new SoftReference<>(buff);
            list.add(sr);
        }
        for (int i = 0; i < 10; i++) {
            System.gc();
        }
        int aliveNums = 0;
        for (SoftReference<byte[]> softReference : list) {
            aliveNums++;
            if (softReference.get() != null) {
                System.out.println(aliveNums);//不会全部输出
            }
        }
    }

    /**
     * 弱引用：只要GC就会清除
     * java.lang.Object@3d82c5f3
     * null
     * null
     * java.lang.ref.WeakReference@2b05039f
     */
    @Test
    public void WeakReferenceTest() throws InterruptedException {
        System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓弱引用↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        Object obj = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(obj, queue);
        System.out.println(queue.poll());//null
        System.out.println(weak.get());//not null
        obj = null;
        for (int i = 0; i < 20; i++) {
            System.gc();
        }
        System.out.println(queue.poll());//not null
        System.out.println(weak.get());//null
    }

    @Test
    public void PhantomReference() {
        System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓虚引用↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        Object obj = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantom = new PhantomReference<>(obj, queue);
        System.out.println(queue.poll());//null
        System.out.println(phantom.get());//null
        obj = null;
        for (int i = 0; i < 20; i++) {
            System.gc();
        }
        System.out.println(queue.poll());//not null
        System.out.println(phantom.get());//null
    }

    @Test
    public void WeakHashMap() {
        //java.util
        WeakHashMap<Integer, Integer> weakHashMap = new WeakHashMap<>();
    }
}
