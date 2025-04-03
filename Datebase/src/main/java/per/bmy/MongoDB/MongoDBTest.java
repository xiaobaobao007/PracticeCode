package per.bmy.MongoDB;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bson.Document;

public class MongoDBTest {

    static String COLLECTION_NAME = "user";

    public static void main(String[] args) {
        try {
            // MongoCredential credential = MongoCredential.createCredential("bmy", "admin", "".toCharArray());
            MongoClientSettings settings = MongoClientSettings.builder()
                    // .credential(credential)
                    .applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(new ServerAddress("localhost", 27017))))
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);

//			ServerAddress serverAddress = new ServerAddress("192.168.0.222", 27017);
//			MongoClient mongoClient = new MongoClient(Collections.singletonList(serverAddress), Collections.singletonList(credential));

            MongoDatabase mongoDatabase = mongoClient.getDatabase("ceshidata");

            //创建集合
            // create(mongoDatabase);

            //新增数据
            // add(mongoDatabase);

            //查看数据
            // findAll(mongoDatabase);

            //查找单个数据
            findOne(mongoDatabase);

            //修改数据
            // update(mongoDatabase);

            //删除数据
            // delete(mongoDatabase);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(MongoDatabase a) {
        try {
            MongoIterable<String> strings = a.listCollectionNames();
            a.createCollection(COLLECTION_NAME);
            System.out.println("集合创建成功");
        } catch (Exception e) {
            System.err.println("！！集合创建异常：" + e.getMessage());
        }
    }

    public static void findAll(MongoDatabase a) {
        try {
            MongoCollection<Document> list = a.getCollection(COLLECTION_NAME);
            FindIterable<Document> findIterable = list.find();
            for (Document document : findIterable) {
                System.out.println("查询的数据:" + document);
            }
        } catch (Exception e) {
            System.err.println("！！数据查询异常：" + e.getMessage());
        }
    }

    public static void findOne(MongoDatabase a) {
        // 筛选的数据
        Document document = new Document();
        document.append("age", 30);

        // 筛选的列数据
        Document name = new Document();
        name.append("age", 1);

        try {
            MongoCollection<Document> list = a.getCollection(COLLECTION_NAME);
            FindIterable<Document> findIterable = list.find(document).projection(name);
            for (Document one : findIterable) {
                System.out.println("查询的数据:" + one);
            }
        } catch (Exception e) {
            System.err.println("！！数据查询异常：" + e.getMessage());
        }
    }

    public static void add(MongoDatabase a) {
        try {
            //新增两个学生和教师的数据
            MongoCollection<Document> collections = a.getCollection(COLLECTION_NAME);
            Document document1 = new Document("name", "学生").append("age", 18).append("type", "学生").append("likesport", "打乒乓球");
            Document document2 = new Document("name", "学生").append("age", 19).append("type", "学生").append("likesport", "打羽毛球");
            Document document3 = new Document("name", "老师").append("age", 33).append("type", "教师").append("likeTv", "湖南Tv");
            Document document4 = new Document("name", "老师").append("age", 30).append("type", "教师").append("likeTv", "星空卫视");
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
            MongoCollection<Document> mongoCollection = a.getCollection(COLLECTION_NAME);
            //修改满足条件的第一条数据
            mongoCollection.updateOne(Filters.eq("name", "老师"), new Document("$set", new Document("address", "深圳市福田区")));
            //修改所以满足条件的数据
            mongoCollection.updateMany(Filters.eq("name", "老师"), new Document("$set", new Document("likeTv", "CCTV-1")));
            FindIterable<Document> findIterable = mongoCollection.find();
            for (Document document : findIterable) {
                System.out.println("更新后的数据:" + document);
            }
        } catch (Exception e) {
            System.err.println("！！数据更新异常：" + e.getMessage());
        }
    }

    public static void delete(MongoDatabase a) {
        try {
            MongoCollection<Document> collection = a.getCollection(COLLECTION_NAME);
            //删除符合条件的第一个文档
            collection.deleteOne(Filters.eq("name", "老师"));
            //删除所有符合条件的文档
            collection.deleteMany(Filters.eq("name", "学生"));
            //检索查看结果
            FindIterable<Document> findIterable = collection.find();
            for (Document document : findIterable) {
                System.out.println("删除后的数据：" + document);
            }
        } catch (Exception e) {
            System.err.println("！！数据删除异常：" + e.getMessage());
        }
    }

}