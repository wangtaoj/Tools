package com.waston.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Redis工具类
 * @Author wangtao
 * @Date 2018/1/29
 **/
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * 设置key的有效期，单位是秒
     * @param key
     * @param expireTime
     * @return
     */
    public static Long expire(String key,int expireTime){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key,expireTime);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            logger.error("expire key:{} error",key,e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    /**
     * 添加key的同时设置过期时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public static String setEx(String key,String value,int expireTime){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key,expireTime,value);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            logger.error("setex key:{} value:{} error",key,value,e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    /**
     * 添加key, value
     * @param key
     * @param value
     * @return
     */
    public static String set(String key,String value){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key,value);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            logger.error("set key:{} value:{} error",key,value,e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    /**
     * 设置之前先判断
     * @param key
     * @param value
     * @return 如果key存在, return false, else return true
     */
    public static boolean setnx(String key,String value){
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = RedisPool.getJedis();
            res = jedis.setnx(key,value);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            logger.error("setnx key:{} value:{} error",key,value,e);
            RedisPool.returnBrokenResource(jedis);
        }
        return (res != null && res > 0);
    }

    /**
     * 获取key所对应的value
     * @param key
     * @return
     */
    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            logger.error("get key:{} error",key,e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    /**
     * 设置之前先获取旧的值
     * @param key
     * @param value
     * @return key已存在返回旧值, 反则返回null
     */
    public static String getSet(String key, String value){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.getSet(key, value);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            logger.error("getSet key:{} error",key,e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    /**
     * 删除key
     * @param key
     * @return
     */
    public static Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            logger.error("del key:{} error",key,e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }
}
