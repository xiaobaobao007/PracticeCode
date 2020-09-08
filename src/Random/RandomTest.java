package Random;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/9/6，15:28
 */
public class RandomTest {

	@Test
	public void longTest() {
		for (int i = 0; i < 20; i++) {
			System.out.print(get(2, 1, 4));
		}
	}

	public int get(int id, int min, int max) {
		if (id < min || id > max || max <= min) {
			return -1;
		}
		if (max == min + 1) {
			if (min == id) {
				return max;
			} else {
				return min;
			}
		} else {
			int n = id + nextInt(0, max - min) + 1;
			if (n > max) {
				n = min + (n - max) - 1;
			}
			return n;
		}
	}

	public int nextInt(int startInclusive, int endExclusive) {
		return startInclusive == endExclusive ? startInclusive : startInclusive + new Random().nextInt(endExclusive - startInclusive);
	}

	@Test
	public void test() {
		System.out.println(parseDate("2020-09-07"));


		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
	}

	public static int parseDate(String dateStr) {
		try {
			return (int) (new SimpleDateFormat("yyyyMMdd").parse(dateStr).getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

}


