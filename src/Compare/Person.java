package Compare;

import java.io.Serializable;
import java.util.Comparator;

public class Person implements Serializable, Comparable<Person>, Comparator<Person> {//实体

	/**
	 *
	 */
	private static final long serialVersionUID = 3193754045080382621L;

	private String name;
	private Integer age;
	private Integer sex;
	private String school;
	private String[] hobby;

	public Person() {
	}

	public Person(Integer age) {
		this.age = age;
	}

	public Person(Integer sex, Integer age) {
		this.sex = sex;
		this.age = age;
	}

	public Person(String name, Integer sex, Integer age, String school, String[] hobby) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.school = school;
		this.hobby = hobby;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	@Override
	public int compareTo(Person o) {
		return this.getAge().equals(o.getAge()) ? this.getSex() - o.getSex() : this.getAge() - o.getAge();
	}

	@Override
	public String toString() {
		return "{" + "age=" + age + ",sex=" + sex + '}';
	}

	@Override
	public int compare(Person o1, Person o2) {
		return o1.getAge().equals(o2.getAge()) ? o1.getSex() - o2.getSex() : o1.getAge() - o2.getAge();
	}
}