package per.bmy.Jedis;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import redis.clients.jedis.*;

/**
 * Jedis测试
 *
 * @author xiaobaobao
 * @date 2019/7/11 16:33
 */
public class JedisDemoTest {

    @Test
    public void test1() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        long start = System.currentTimeMillis();
        jedis.flushAll();
        for (int i = 0; i < 10000; i++) {
            jedis.set("" + i, "" + i);
        }
        System.out.println(System.currentTimeMillis() - start);
        jedis.close();
    }

    @Test
    public void test2() {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(30);
        config.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("name", "test2");
            System.out.println(jedis.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            jedisPool.close();
        }
    }


    @Test
    public void test3() throws InterruptedException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushAll();
        jedis.close();

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);
        config.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);

        int threads = 3;
        int nums = 10000;
        CountDownLatch latch = new CountDownLatch(threads);
        Runnable runnable = () -> {
            Jedis jedis1 = jedisPool.getResource();
            for (int i = 0; i < nums; i++) {
                jedis1.set("" + i, "" + i);
            }
            latch.countDown();
            jedis1.close();
        };
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            new Thread(runnable).start();
        }
        latch.await();
        System.out.println(System.currentTimeMillis() - start);

    }

    transient static int time = 10;

    @Test
    public void test4() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        new Thread(() -> {
            while (--time > 5) {
                synchronized (jedis) {
                    jedis.lpush("KEY", String.valueOf(time));
                }
            }
        }).start();

        new Thread(() -> {
            while (time > 0) {
                synchronized (jedis) {
                    System.out.println(jedis.brpop(20, "KEY"));
                    time--;
                }
            }
            jedis.close();
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test5() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Transaction t = jedis.multi();
        t.set("a", "abc");
        t.exec();

        ScanResult<String> scan = jedis.scan("0");
        System.out.println(scan.getCursor());
        System.out.println(scan.getResult());
    }

    @Test
    public void addCluster() throws IOException {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(50);

        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379));
        shards.add(new JedisShardInfo("127.0.0.1", 6380));
        shards.add(new JedisShardInfo("127.0.0.1", 6381));
        shards.add(new JedisShardInfo("127.0.0.1", 6382));
        shards.add(new JedisShardInfo("127.0.0.1", 6383));
        shards.add(new JedisShardInfo("127.0.0.1", 6384));

        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(poolConfig, shards);
        ShardedJedis shardedJedis = null;

        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
            System.out.println(shardedJedis.set("a", "qwe"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                shardedJedis.close();
            }
        }
        shardedJedisPool.close();
    }

    @Test
    public void testJedisCluster() {
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        nodes.add(new HostAndPort("127.0.0.1", 6380));
        nodes.add(new HostAndPort("127.0.0.1", 6381));
        nodes.add(new HostAndPort("127.0.0.1", 6382));
        nodes.add(new HostAndPort("127.0.0.1", 6383));
        nodes.add(new HostAndPort("127.0.0.1", 6384));

        JedisCluster cluster = new JedisCluster(nodes);

        cluster.set("key1", "1000");
        System.out.println(cluster.get("key1"));

        cluster.close();
    }

    @Test
    public void pipeCompare() {
        Jedis redis = new Jedis("127.0.0.1", 6379);
//		redis.auth("12345678");//授权密码 对应redis.conf的requirepass密码
        Map<String, String> data = new HashMap<String, String>();
//		redis.select(8);//使用第8个库
//		redis.flushDB();//清空第8个库所有数据
        // hmset
        long start = System.currentTimeMillis();
        // 直接hmset
        for (int i = 0; i < 10000; i++) {
            data.clear();  //清空map
            data.put("k_" + i, "v_" + i);
            redis.hmset("key_" + i, data); //循环执行10000条数据插入redis
        }
        long end = System.currentTimeMillis();
        System.out.println("    共插入:[" + redis.dbSize() + "]条 .. ");
        System.out.println("1,未使用PIPE批量设值耗时" + (end - start) / 1000 + "秒..");
        redis.select(8);
        redis.flushDB();
        // 使用pipeline hmset
        Pipeline pipe = redis.pipelined();
        start = System.currentTimeMillis();
        //
        for (int i = 0; i < 10000; i++) {
            data.clear();
            data.put("k_" + i, "v_" + i);
            pipe.hmset("key_" + i, data); //将值封装到PIPE对象，此时并未执行，还停留在客户端
        }
        pipe.sync(); //将封装后的PIPE一次性发给redis
        end = System.currentTimeMillis();
        System.out.println("    PIPE共插入:[" + redis.dbSize() + "]条 .. ");
        System.out.println("2,使用PIPE批量设值耗时" + (end - start) / 1000 + "秒 ..");
//--------------------------------------------------------------------------------------------------
        // hmget
        Set<String> keys = redis.keys("key_*"); //将上面设值所有结果键查询出来
        // 直接使用Jedis hgetall
        start = System.currentTimeMillis();
        Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
        for (String key : keys) {
            //此处keys根据以上的设值结果，共有10000个，循环10000次
            result.put(key, redis.hgetAll(key)); //使用redis对象根据键值去取值，将结果放入result对象
        }
        end = System.currentTimeMillis();
        System.out.println("    共取值:[" + redis.dbSize() + "]条 .. ");
        System.out.println("3,未使用PIPE批量取值耗时 " + (end - start) / 1000 + "秒 ..");

        // 使用pipeline hgetall
        result.clear();
        start = System.currentTimeMillis();
        for (String key : keys) {
            pipe.hgetAll(key); //使用PIPE封装需要取值的key,此时还停留在客户端，并未真正执行查询请求
        }
        pipe.sync();  //提交到redis进行查询

        end = System.currentTimeMillis();
        System.out.println("    PIPE共取值:[" + redis.dbSize() + "]条 .. ");
        System.out.println("4,使用PIPE批量取值耗时" + (end - start) / 1000 + "秒 ..");

        redis.disconnect();
    }


}

/*
  string
  set key value
  get key
  del key
  incr key--------------------存在值加1，否则设置0
  decr key--------------------存在值减1，否则设置0
  incrby key increment--------------------存在值加increment，否则设置0+increment
  decrby key increment--------------------存在值减increment，否则设置0-increment
  append key value--------------------字符串往后加字符串
  strlen key
  ================================================================================
  hash
  hset key field value
  hmset key field value1 age value2
  hget key field
  hdel key field
  del key
  hincrby key field value--------------------增加数值
  hgetall key
  hlen key
  hkeys key
  ================================================================================
  list(arraylist,linkedlist)
  lpush key value--------------------头部添加
  rpush key value--------------------尾部添加
  lrange key value1 value2--------------------索引的范围
  lpop key--------------------头部元素移出
  rpop key--------------------尾部元素移出
  llen key--------------------集合大小
  lpushx key value--------------------列表存在再插入
  del key
  lrem key count value--------------------删除num个value ,num>0从前删除，<0从后删除，=0删除所有
  lset key index value--------------------改变指定位置元素
  linsert key after value1 value2 在2后面插入1--------------------向指定元素后插入数据
  rpoplpush key1 key2--------------------1的尾部取出放入2的头部，适用于消息队列
  ================================================================================
  set
  sadd key member
  srem key member
  smembers key
  sismember name member--------------------0不存在，1存在
  sdiff key key1--------------------求差集
  sinter key key1--------------------求交集
  sunion key key1--------------------求并集
  scard key--------------------数量
  sdiffstore key key1 key2--------------------求差集赋值给key
  sinterstore key key1 key2 求交集赋值给key
  sunionstore key key1 key2 求并集赋值给key
  ================================================================================
  zset
  zadd key score1 member1 score2 member2
  zscore key member
  zcard key--------------------数量
  zrem key member1 member2--------------------删除
  zrange key rank1 rank2 (withscores)--------------------index范围输出
  zrangebyscore key score1 score2 (limit offset count)--------------------分数范围
  zremrangebyrank key rank1 rank2--------------------排名范围
  zremrangebyscore key score1 score2--------------------分数排名
  zincrby key increment member//此处先写分数再写键值
  ================================================================================
  通用键的操作
  keys *
  keys my*
  keys my?
  exists key
  rename oldname newname
  expire key mm
  ttl key 所剩余过期时间（没有设置-1）
  type key
  ================================================================================
  数据库操作
  默认的是0数据库
  select index选择第几个数据库
  move name index移动到第几个数据库
  multi--------------------开启事务
  exec--------------------提交
  discard--------------------回滚
  ================================================================================
  持久化操作
  1、RDB
  在指定的时间内把内存数据写入硬盘
  优点：大数据的读取
  缺点：宕机时会让数据丢失
  2、AOF
  每个操作写入日志
  优点：安全
  缺点：效率低
  --------------------
  shutdown
  cd C:\Users\Administrator\Desktop\redis64-3.0.501
  redis-server.exe redis.windows.conf
  --------------------
  3、同时使用RDB和AOF
  4、无持久化内存缓存机制
  <p>
  <p>
  ================================================================================
  NoSql=not only sql:非关系型数据库
  <p>
  SNS网站：社交网站服务
  <p>
  非关系型数据库：
  mongoDB(文档数据库，查询性能低，没有统一查询语句) redis（键值对）
  高并发读写，海量数据的存储和访问，高可扩展性和高可用性，易扩展，灵活的数据模型
  <p>
  缓存，任务队列，应用排行榜，网站访问统计，数据过期处理，分布式集群架构中的session分离
  <p>
  key:不能太长也不能太短，要有统一的命名的规范，1024字节
  value：512M
 */