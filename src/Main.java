import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Main{
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        Person t = new Person("1",1,1,"1",null);
        Map<String,String> describe = BeanUtils.describe(t);////此方法需要两个jar包：commons-beanutils.jar commons-logging.jar
        for (Map.Entry<String, String> entry : describe.entrySet()) {
            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
        }
    }
}