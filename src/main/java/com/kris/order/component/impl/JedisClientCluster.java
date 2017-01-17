package com.kris.order.component.impl;

import com.kris.order.component.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * redis客户端集群版实现类
 *
 * @author kris
 * @create 2016-12-28 17:18
 */
public class JedisClientCluster implements JedisClient {

    @Autowired
    private JedisCluster mJedisCluster;

    @Override
    public String set(String key, String value) {
        return mJedisCluster.set(key,value);
    }

    @Override
    public String get(String key) {
        return mJedisCluster.get(key);
    }

    @Override
    public Long hset(String key, String item, String value) {
        return mJedisCluster.hset(key, item, value);
    }

    @Override
    public String hget(String key, String item) {
        return mJedisCluster.hget(key, item);
    }

    @Override
    public Long incr(String key) {
        return mJedisCluster.incr(key);
    }

    @Override
    public Long decr(String key) {
        return mJedisCluster.decr(key);
    }

    @Override
    public Long expire(String key, int second) {
        return mJedisCluster.expire(key, second);
    }

    @Override
    public Long ttl(String key) {
        return mJedisCluster.ttl(key);
    }

    @Override
    public Long hdel(String key, String item) {
        return mJedisCluster.hdel(key,item);
    }
}
