package Compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author xiaobaobao
 * @date 2019/6/24 9:36
 * 都能使用lambdas
 */
public class Main {
	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person(2, 25));
		personList.add(new Person(2, 20));
		personList.add(new Person(1, 10));
		personList.add(new Person(2, 10));
		personList.add(new Person(1, 20));
		personList.add(new Person(2, 15));
		personList.add(new Person(1, 25));
		personList.add(new Person(1, 15));

		//传统比较方法,都是前边减后边的，则递增排序
		Collections.sort(personList, (o1, o2) -> o1.getAge().equals(o2.getAge()) ? o1.getSex() - o2.getSex() : o1.getAge() - o2.getAge());
		sout(personList);
		swap(personList);

		//利用comparable-compareTo
		Collections.sort(personList);
		sout(personList);
		swap(personList);

		//利用comparator-compare
		personList.sort(new Person());
//		personList.sort((a, b) -> a.getAge() - b.getAge());
		sout(personList);
		swap(personList);

		Collections.sort(personList, new Person());
		sout(personList);
		swap(personList);
	}

	public static void swap(List list) {
		int size = list.size();
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			int a = random.nextInt(size);
			int b = random.nextInt(size);
			Object o = list.get(a);
			list.set(a, list.get(b));
			list.set(b, o);
		}
	}

	public static void sout(List list) {
		for (Object o : list) {
			System.out.print(o.toString() + " ");
		}
		System.out.println();
	}
}