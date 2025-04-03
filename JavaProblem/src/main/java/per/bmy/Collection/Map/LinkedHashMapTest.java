package per.bmy.Collection.Map;

import java.util.LinkedHashMap;

/**
 * @author xiaobaobao
 * @date 2019/10/19，12:05
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {
        //第三个参数为是否为访问顺序,true访问顺序，false插入顺序，默认fasle
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(10, 1.0F, false);
        map.put(1, 1);
    }

}
