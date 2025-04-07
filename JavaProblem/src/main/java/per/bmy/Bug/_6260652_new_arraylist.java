package per.bmy.Bug;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *
 *
 *             public ArrayList(Collection<? extends E> c) {
 *                 elementData = c.toArray();
 *                 if ((size = elementData.length) != 0) {
 *                     // c.toArray might (incorrectly) not return Object[] (see 6260652)
 *                     if (elementData.getClass() != Object[].class)
 *                         elementData = Arrays.copyOf(elementData, size, Object[].class);
 *                 } else {
 *                     // replace with empty array.
 *                     this.elementData = EMPTY_ELEMENTDATA;
 *                 }
 *             }
 *
 *
 *             总结：此bug提示主要因为调用toArray、可能不是Object类型、可能为类自己的实现。从而导致后续数据插入失败
 *
 * </pre>
 *
 * @author baomengyang
 * @date 2025/4/7 13:56:42
 */
public class _6260652_new_arraylist {

    public static class MyList<E> extends ArrayList<E> {
        // toArray() 的同名方法
        public String[] toArray() {
            return new String[]{"1", "2", "3"};
        }
    }

    public static void main(String[] args) {
        List<String> ss = new LinkedList<String>();             // LinkedList toArray() 返回的本身就是 Object[]
        ss.add("123");
        Object[] objs = ss.toArray();
        objs[0] = new Object();

        // 此处说明了：c.toArray might (incorrectly) not return Object[] (see 6260652)
        ss = new MyList<String>();
        objs = ss.toArray();
        System.out.println(objs.getClass());        // class [Ljava.lang.String;
        objs[0] = new Object();                         // java.lang.ArrayStoreException: java.lang.Object
    }
}
