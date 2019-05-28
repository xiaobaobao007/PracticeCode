package Serialize;

import java.io.*;
import java.text.MessageFormat;

public class Main {

    private static String path = "./src/resouse/Breads.txt";

    public static void main(String[] args) {
        try {
            SerializePerson();//序列化Person对象
            Person p = DeserializePerson();//反序列Perons对象
            System.out.println(MessageFormat.format("name={0},age={1},sex={2}",
                    p.getName(), p.getAge(), p.getSex()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void SerializePerson() throws
            IOException {
        Person person = new Person();
        person.setName("gacl");
        person.setAge(25);
        person.setSex("男");
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        File r = new File(path);
        FileOutputStream fileOutputStream = new FileOutputStream(r);
        ObjectOutputStream oo = new ObjectOutputStream(fileOutputStream);
        oo.writeObject(person);
        System.out.println("Person对象序列化成功！");
        oo.close();
    }

    private static Person DeserializePerson() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File(path)));
        Person person = (Person) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        return person;
    }

}