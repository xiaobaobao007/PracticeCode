package Num17;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 是否是17的倍数
 */
public class Main {

	public static void main(String[] args) {
		new Main();
	}

	BigDecimal min = new BigDecimal(0);
	BigDecimal five = new BigDecimal(5);
	BigDecimal ten = new BigDecimal(10);
	BigDecimal max = new BigDecimal(17);

	Main() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			BigDecimal bigDecimal = scanner.nextBigDecimal();
			if (bigDecimal.equals(min)) {
				break;
			}
			System.out.println(big(bigDecimal));
		}
	}

	public int big(BigDecimal bigDecimal) {
		bigDecimal = bigDecimal.abs();
		if (bigDecimal.compareTo(max) == 0 || bigDecimal.compareTo(min) == 0) {
			return 1;
		} else if (bigDecimal.compareTo(max) < 0) {
			return 0;
		} else {
			BigDecimal[] bigDecimals = bigDecimal.divideAndRemainder(ten);
			return big(bigDecimals[0].subtract(bigDecimals[1].multiply(five)));
		}
	}
}