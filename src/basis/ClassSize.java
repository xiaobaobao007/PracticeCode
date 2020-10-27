package basis;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author xiaobaobao
 * @date 2020/10/21，11:19
 */
public class ClassSize {
	public class A {
	}

	public class B {
		int a;
	}

	public class C {
		boolean a = true;
		byte b = 1 << 1;
		short c = 1 << 2;
		int d = 1 << 3;
		long e = 1 << 4;
		float f = 1.5F;
		double g = 1.05F;
		char h = 'A';
	}

	public class D {
		int[] a = new int[]{1, 2, 3};
	}

	@Test
	public void main() {
		// System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒1");
		// System.out.println(ClassLayout.parseInstance(new A()).toPrintable());
		// System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒2");
		// System.out.println(ClassLayout.parseInstance(new B()).toPrintable());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒3");
		System.out.println(ClassLayout.parseInstance(new C()).toPrintable());
		// System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒4");
		// System.out.println(ClassLayout.parseInstance(new D()).toPrintable());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒5");
		System.out.println(ClassLayout.parseInstance(new int[0]).toPrintable());
	}

}
