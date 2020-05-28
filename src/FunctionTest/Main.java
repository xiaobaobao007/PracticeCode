package FunctionTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author xiaobaobao
 * @date 2020/5/28，19:15
 */
public class Main {

	static class UserT {
		String name;

		public UserT() {
		}

		public UserT(String zm) {
			this.name = zm;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	@Test
	public void Consumer() {
		UserT userT = new UserT("1");
		Consumer<UserT> consumer = a -> a.setName("2");
		System.out.println(userT.getName());
		consumer.accept(userT);
		System.out.println(userT.getName());
	}

	@Test
	public void Predicate() {
		UserT userT = new UserT();
		Predicate<UserT> predicate = a -> a.getName() == null;
		System.out.println(predicate.test(userT));
		userT.setName("1");
		System.out.println(predicate.test(userT));
	}

	@Test
	public void Predicate_List() {
		List<String> listTest = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

		Predicate<String> startsWithJ = n -> n.startsWith("J");
		Predicate<String> length4 = n -> n.length() == 4;

		listTest.stream().filter(startsWithJ.and(length4)).forEach(System.out::println);
	}

	@Test
	public void Supplier() {
		UserT userT = new UserT("1");
		Supplier<String> predicate = userT::getName;
		System.out.println(predicate.get());
	}


	public static void main(String[] args) {
		Predicate<Supplier<String>> predicate = a -> a.get() != null;
		UserT userT = new UserT("1");
		Supplier<String> supplier = userT::getName;

		System.out.println(predicate.test(supplier));
	}

	@Test
	public void mainqwe() {
		int incr = 20;
		int myNumber = 10;
		modifyTheValue(myNumber, val -> val + incr);
	}

	public void modifyTheValue(int valueToBeOperated, Function<Integer, Integer> function) {
		int newValue = function.apply(valueToBeOperated);
		System.out.println(newValue);
	}

}
