package Util;

import java.util.Random;

/**
 * @author xiaobaobao
 * @date 2020/9/20，17:23
 */
public class RandomUtil {
	private static final Random RANDOM = new Random();

	public static int nextInt(int i) {
		return RANDOM.nextInt(i);
	}

	public static boolean getTrue() {
		return RANDOM.nextInt(2) == 0;
	}
}
