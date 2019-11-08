package Others;

/**
 * @author xiaobaobao
 * @date 2019/8/20 18:13
 */
public class HashCode {
	public static void main(String[] args) {
		Integer a = 32768;
		System.out.println(a.hashCode());
		System.out.println(a.hashCode() >>> 16);
		System.out.println(a.hashCode() ^ (a.hashCode() >>> 16));
		Integer b = 65536;
		System.out.println(b.hashCode());
		System.out.println(b.hashCode() >>> 16);
		System.out.println(b.hashCode() ^ (b.hashCode() >>> 16));
	}
}
