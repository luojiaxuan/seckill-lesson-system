package com.example.two.twodemo.redis;

import com.example.two.twodemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.concurrent.TimeUnit;

@Repository
public class UserRedis extends BaseRedis<User>{
//    @Autowired
//    public RedisTemplate<String,Object> redisTemplate;
    private static final String REDIS_KEY = "com.example.two.twodemo.UserRedis";
    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }

    public void add(String key,Long time,User user){
        Gson gson =new Gson();
        redistTemplate.opsForValue().set(key,gson.toJson(user),time, TimeUnit.SECONDS);
    }
}
