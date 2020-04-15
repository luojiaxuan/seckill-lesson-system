package com.example.two.twodemo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class BaseRedis<T> {
    @Autowired
    protected RedisTemplate<String ,Object> redistTemplate;
    @Resource
    protected HashOperations<String,String,T> hashOperations;

    protected  abstract String getRedisKey();

    public void put(String key,T domain,long expire){
        hashOperations.put(getRedisKey(),key,domain);
        if(expire!=-1){
            redistTemplate.expire(getRedisKey(),expire, TimeUnit.SECONDS);
        }
    }

    //删除
    public void remove(String key){
        hashOperations.delete(getRedisKey(),key);
    }

    //查询
    public T get(String key){
        return hashOperations.get(getRedisKey(),key);
    }

    //获取当前redis库所有对象
    public List<T> getAll(){
        return hashOperations.values(getRedisKey());
    }

    //查询当前redis所有key
    public Set<String> getKeys(){
        return hashOperations.keys(getRedisKey());
    }

    //判断key是否在redis中
    public boolean isKeyExists(String key){
        return hashOperations.hasKey(getRedisKey(),key);
    }

    //查询当前key下缓存数量
    public long count(){
        return hashOperations.size(getRedisKey());
    }

    //清空redis
    public void empty(){
        Set<String> set= hashOperations.keys(getRedisKey());
        set.stream().forEach(key->hashOperations.delete(getRedisKey(),key));
    }
}
