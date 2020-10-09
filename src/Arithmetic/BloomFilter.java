package Arithmetic;

import java.util.BitSet;

/**
 * @author xiaobaobao
 * @date 2020/6/2，22:43
 * <p>
 * 布隆过滤器
 */
public class BloomFilter {
	private static final int SIZE = 0x7FFFF;
	private static final int[] seeds = new int[]{5, 7, 11, 13, 31, 37, 61};
	private final BitSet bits = new BitSet(SIZE);
	private final SimpleHash[] func = new SimpleHash[seeds.length];

	public BloomFilter() {
		for (int i = 0; i < seeds.length; i++) {
			func[i] = new SimpleHash(seeds[i]);
		}
	}

	public void add(String value) {
		for (SimpleHash f : func) {
			bits.set(f.hashAndGetIndex(value));
		}
	}

	public boolean contains(String value) {
		if (value == null) {
			return false;
		}
		for (SimpleHash f : func) {
			if (!bits.get(f.hashAndGetIndex(value))) {
				return false;
			}
		}
		return true;
	}

	// 内部类，simpleHash
	public static class SimpleHash {

		private final int seed;

		public SimpleHash(int seed) {
			this.seed = seed;
		}

		public int hashAndGetIndex(String value) {
			int result = 0;
			int len = value.length();
			for (int i = 0; i < len; i++) {
				result = seed * result + value.charAt(i);
			}
			return SIZE & result;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println(new BloomFilter().hashCode());
		}
		// bf.add("rewc");
		// bf.add("test");
		// bf.add("tefdsafst");
		// bf.add("vzcv");
		// bf.add("ewfa");
		// bf.add("tesvart");
		//
		// System.out.println(bf.contains("343"));
		// System.out.println(bf.contains("vzcv"));
	}

}