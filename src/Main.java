import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaobaobao
 * @date 2020/4/28，19:48
 */
public class Main {
	public static void main(String[] args) {
		Map<Integer, Integer> map = new HashMap<>();
		map.computeIfAbsent(1, (k) -> {
			System.out.println(k);
			return 1;
		});
		System.out.println(map);
	}
}
