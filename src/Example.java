import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author xiaobaobao
 * @date 2019/6/28 14:38
 */
public class Example {
    public static void main(String args[]) {

        Map<Integer, Integer> map = new HashMap<>(16, 1.0F);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int j = random.nextInt(150);
            map.put(j, j);
//            map.put(i,i);
        }
        for (Integer i : map.keySet()) {
            System.out.println(i);
        }
    }
}