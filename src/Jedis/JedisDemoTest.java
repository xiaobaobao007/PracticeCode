package Jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CountDownLatch;

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
			jedisPool.returnResourceObject(jedis);
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