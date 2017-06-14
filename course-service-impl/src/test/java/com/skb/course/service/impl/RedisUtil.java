package com.skb.course.service.impl;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisUtil {

	public static Jedis jedis;

	/**
	 * 获取jedis连接
	 * 
	 * @param host
	 * @param port
	 * @return
	 */
	public static Jedis getJedis(String host, int port) {
		jedis = new Jedis(host, port);
		return jedis;
	}

	/**
	 * 批量删除一个节点上的符合规则的数据
	 * 
	 * @param jedis
	 * @param pattern
	 */
	public static void delByKeysPattern(Jedis jedis, String pattern) {
		Set<String> sets = jedis.keys(pattern);
		Pipeline pp = jedis.pipelined();
		for (String ss : sets) {
			System.out.println(ss);
			pp.del(ss);
		}
		pp.sync();
		jedis.quit();
	}

}
