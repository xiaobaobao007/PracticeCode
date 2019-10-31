package MongoDB;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MongoDBTest {

    static String Collection_Name = "user";

    public static void main(String args[]) {
        try {

            ServerAddress serverAddress = new ServerAddress("192.168.0.222", 27017);
            MongoCredential credential = MongoCredential.createScramSha1Credential("bmy", "admin", "password".toCharArray());
            MongoClient mongoClient = new MongoClient(Collections.singletonList(serverAddress), Collections.singletonList(credential));
            MongoDatabase mongoDatabase = mongoClient.getDatabase("ceshidata");

            create(mongoDatabase);//创建集合
            add(mongoDatabase);//新增数据
            find(mongoDatabase);//查看数据
            update(mongoDatabase);//修改数据
            delete(mongoDatabase);//删除数据

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(MongoDatabase a) {
        try {
            a.createCollection(Collection_Name);
            System.out.println("集合创建成功");
        } catch (Exception e) {
            System.err.println("！！集合创建异常：" + e.getMessage());
        }
    }

    public static void find(MongoDatabase a) {
        try {
            MongoCollection<Document> list = a.getCollection(Collection_Name);
            FindIterable<Document> findIterable = list.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println("查询的数据:" + mongoCursor.next());
            }
        } catch (Exception e) {
            System.err.println("！！数据查询异常：" + e.getMessage());
        }

    }

    public static void add(MongoDatabase a) {
        try {
            //新增两个学生和教师的数据
            MongoCollection<Document> collections = a.getCollection(Collection_Name);
            Document document1 = new Document("name", "学生").
                    append("age", 18).
                    append("type", "学生").
                    append("likesport", "打乒乓球");
            Document document2 = new Document("name", "学生").
                    append("age", 19).
                    append("type", "学生").
                    append("likesport", "打羽毛球");
            Document document3 = new Document("name", "老师").
                    append("age", 33).
                    append("type", "教师").
                    append("likeTv", "湖南Tv");
            Document document4 = new Document("name", "老师").
                    append("age", 30).
                    append("type", "教师").
                    append("likeTv", "星空卫视");
            List<Document> documents = new ArrayList<>();
            documents.add(document1);
            documents.add(document2);
            documents.add(document3);
            documents.add(document4);
            collections.insertMany(documents);
            System.out.println("数据插入成功");
        } catch (Exception e) {
            System.err.println("！！数据新增异常：" + e.getMessage());
        }
    }


    public static void update(MongoDatabase a) {
        try {
            MongoCollection<Document> mongoCollection = a.getCollection(Collection_Name);
            //修改满足条件的第一条数据
            mongoCollection.updateOne(Filters.eq("name", "老师"), new Document("$set", new Document("address", "深圳市福田区")));
            //修改所以满足条件的数据
            mongoCollection.updateMany(Filters.eq("name", "老师"), new Document("$set", new Document("likeTv", "CCTV-1")));
            FindIterable<Document> findIterable = mongoCollection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println("更新后的数据:" + mongoCursor.next());
            }
        } catch (Exception e) {
            System.err.println("！！数据更新异常：" + e.getMessage());
        }
    }

    public static void delete(MongoDatabase a) {
        try {
            MongoCollection<Document> collection = a.getCollection(Collection_Name);
            //删除符合条件的第一个文档
            collection.deleteOne(Filters.eq("name", "老师"));
            //删除所有符合条件的文档
            collection.deleteMany(Filters.eq("name", "学生"));
            //检索查看结果
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println("删除后的数据：" + mongoCursor.next());
            }
        } catch (Exception e) {
            System.err.println("！！数据删除异常：" + e.getMessage());
        }
    }


}