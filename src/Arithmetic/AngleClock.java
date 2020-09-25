package Arithmetic;

/**
 * @author xiaobaobao
 * @date 2020/9/24，23:03
 */
public class AngleClock {

	public double angleClock(int hour, int minutes) {
		double m;
		if ((m = Math.abs(((hour + minutes / 60.0) * 5) - minutes)) <= 30) {
			return m * 6.0;
		} else {
			return (60 - m) * 6.0;
		}
	}

	public static void main(String[] args) {
		System.out.println(new AngleClock().angleClock(12, 30));
		System.out.println(new AngleClock().angleClock(3, 30));
		System.out.println(new AngleClock().angleClock(3, 15));
		System.out.println(new AngleClock().angleClock(4, 50));
		System.out.println(new AngleClock().angleClock(12, 0));
	}
}
