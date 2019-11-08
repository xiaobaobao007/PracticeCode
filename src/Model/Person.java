package Model;

import java.io.Serializable;

public class Person implements Serializable {//实体

	/**
	 *
	 */
	private static final long serialVersionUID = 3193754045080382621L;

	private String name;
	private Integer sex;
	private Integer age;
	private String school;
	private String[] hobby;

	public Person(String name) {
		this.name = name;
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

}