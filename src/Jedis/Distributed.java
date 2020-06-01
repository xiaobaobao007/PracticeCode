package Jedis;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.misc.Hash;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobaobao
 * @date 2020/5/11，10:23
 */
public class Distributed {

	@Test
	public void test1() {
		Config config = new Config();
		config.useSentinelServers()
				.addSentinelAddress("127.0.0.1:6369", "127.0.0.1:6379", "127.0.0.1:6389")
				.setMasterName("masterName")
				.setPassword("password").setDatabase(0);
		RedissonClient redissonClient = Redisson.create(config);
		RLock redLock = redissonClient.getLock("REDLOCK_KEY");
//		redissonClient.getFairLock()
//		redissonClient.getReadWriteLock()
		boolean isLock;
		try {
			// 500ms拿不到锁, 就认为获取锁失败。10000ms即10s是锁失效时间。
			isLock = redLock.tryLock(500, 10000, TimeUnit.MILLISECONDS);
			if (isLock) {
				System.out.println("get lock");
			}
		} catch (Exception e) {
		} finally {
			redLock.unlock();
		}
	}

	@Test
	public void test2() {
		Config config1 = new Config();
		config1.useSingleServer().setAddress("redis://192.168.0.1:5378")
				.setPassword("a123456").setDatabase(0);
		RedissonClient redissonClient1 = Redisson.create(config1);

		Config config2 = new Config();
		config2.useSingleServer().setAddress("redis://192.168.0.1:5379")
				.setPassword("a123456").setDatabase(0);
		RedissonClient redissonClient2 = Redisson.create(config2);

		Config config3 = new Config();
		config3.useSingleServer().setAddress("redis://192.168.0.1:5380")
				.setPassword("a123456").setDatabase(0);
		RedissonClient redissonClient3 = Redisson.create(config3);

		String resourceName = "REDLOCK_KEY";

		RLock lock1 = redissonClient1.getLock(resourceName);
		RLock lock2 = redissonClient2.getLock(resourceName);
		RLock lock3 = redissonClient3.getLock(resourceName);

		RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);
		boolean isLock;
		try {
			// 500ms拿不到锁, 就认为获取锁失败。10000ms即10s是锁失效时间。
			isLock = redLock.tryLock(500, 10000, TimeUnit.MILLISECONDS);
			System.out.println("isLock = " + isLock);
			if (isLock) {
				System.out.println("get lock");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			redLock.unlock();
		}
	}

}
