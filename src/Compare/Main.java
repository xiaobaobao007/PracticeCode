package Compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author xiaobaobao
 * @date 2019/6/24 9:36
 */
public class Main {
	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			personList.add(new Person(random.nextInt(1000)));
		}
//        Collections.sort(personList, (o1, o2) -> o2.getAge() - o1.getAge());
		Collections.sort(personList);
		for (Person person : personList) {
			System.out.println(person.getAge());
		}
	}
}
