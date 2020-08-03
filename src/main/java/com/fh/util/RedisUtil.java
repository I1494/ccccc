package com.fh.util;
import redis.clients.jedis.Jedis;
public class RedisUtil {
    public static void del(String key, Integer productId){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            jedis.close();
        }
    }
    public static void setex(String key, int seconds, String value) {
        Jedis jedis =null;
        try {
        jedis = RedisPool.getResource();
        jedis.setex(key,seconds,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            jedis.close();
        }
    }

    public static void set(String key, String value, Long redisExpireTime){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            jedis.close();
        }
    }

    public static String get(String key, Integer id){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            String res = jedis.get(key);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            jedis.close();
        }
    }
}
