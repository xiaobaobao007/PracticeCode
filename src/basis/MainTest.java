package basis;

import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/5/2，22:18
 */
public class MainTest {

	/**
	 * 二进制计算
	 */
	@Test
	public void test1() {
		/**
		 0100000000
		 0010000000
		 1000000000
		 0010000000
		 */
		System.out.println(Integer.toBinaryString(256));
		System.out.println(Integer.toBinaryString(256 >> 1));
		System.out.println(Integer.toBinaryString(256 << 1));
		System.out.println(Integer.toBinaryString(256 >>> 1));
		/**
		 11111111111111111111111100000000
		 11111111111111111111111110000000
		 11111111111111111111111000000000
		 01111111111111111111111110000000
		 */
		System.out.println(Integer.toBinaryString(-256));
		System.out.println(Integer.toBinaryString(-256 >> 1));
		System.out.println(Integer.toBinaryString(-256 << 1));
		System.out.println(Integer.toBinaryString(-256 >>> 1));
		/**
		 00000000000000000000000100000000
		 11111111111111111111111011111111
		 */
		System.out.println(Integer.toBinaryString(256));
		System.out.println(Integer.toBinaryString(~256));
		/**
		 0001
		 1000
		 1001
		 0000
		 1001
		 */
		System.out.println(Integer.toBinaryString(1));
		System.out.println(Integer.toBinaryString(8));
		System.out.println(Integer.toBinaryString(1 | 8));
		System.out.println(Integer.toBinaryString(1 & 8));
		System.out.println(Integer.toBinaryString(1 ^ 8));

	}
}
