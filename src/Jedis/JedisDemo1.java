package Jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis测试
 *
 * @author xiaobaobao
 * @date 2019/7/11 16:33
 */
public class JedisDemo1 {
    @Test
    public void demo1() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("name", "xbb");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }

    @Test
    public void demo2() {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(30);
        config.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        Jedis jedis=null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("name", "demo2");
            System.out.println(jedis.get("name"));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
    }
}
