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

//		Collections.sort(personList, (o1, o2) -> o2.getAge() - o1.getAge());
		Collections.sort(personList);
		for (Person person : personList) {
			System.out.println(person.toString());
		}
	}
}