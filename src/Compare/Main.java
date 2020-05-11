package Compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xiaobaobao
 * @date 2019/6/24 9:36
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
		Collections.sort(personList, (o1, o2) -> o1.getAge() - o2.getAge());
		//利用comparable-compareTo
//		Collections.sort(personList);

		//利用comparator-compare
//		personList.sort(new Person());
//		Collections.sort(personList, new Person());

		for (Person person : personList) {
			System.out.println(person.toString());
		}
	}
}