package per.bmy.MongoDB;

import org.bson.Document;
import org.junit.Test;

/**
 * @author bao meng yang <932824098@qq.com>
 * @date 2021/8/12，14:52:22
 */
public class DocumentTest {

    @Test
    public void main() {
        Document document = new Document("name", "学生").append("age", 18).append("type", "学生").append("likesport", "打乒乓球");
        System.out.println(document.toString());
        document.append("age", 324);
        System.out.println(document.toString());
    }

}
