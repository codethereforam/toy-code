package priv.thinkam.toycode.redis;

import redis.clients.jedis.Jedis;

/**
 * @author thinkam
 * @date 2018/08/23
 */
public class Test1 {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		System.out.println(value);
	}

}
