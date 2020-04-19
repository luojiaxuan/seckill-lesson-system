package com.example.two.twodemo;

import com.example.two.twodemo.VO.UserVO;
import com.example.two.twodemo.model.User;
import com.example.two.twodemo.redis.UserRedis;
import com.example.two.twodemo.service.UserService;
import com.example.two.twodemo.service.impl.UserServiceImpl;
import com.example.two.twodemo.util.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HelloWorldTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRedis userRedis;
    //@Test
//    public void testRegistory(){
//        String username = "123";
//        String password = "1234";
//        Assert.assertNotNull(userService.regist(new User(username,password)));
//    }
    @Test
    public void testGetUser(){
        Assert.assertNotNull(userService.getUser("1234"));
    }
    @Test
    public void testPassword(){
        User user = userService.getUser("1234");
        String inputPassword = MD5Util.inputToDb("1234",user.getSalt());
        Assert.assertTrue(inputPassword.equals(user.getPassword()));
    }
    @Test
    public void testRedis(){
        User user =new User("1","123456");
        userRedis.put(user.getUsername(),user,-1);
        System.out.println(userRedis.get("1").toString());
    }

    /**
     * 测试加法
     */
    @Test
    public void testAdd(){
        long a=0;
        float b= (float) 0.1;
        Assert.assertEquals(0,add(a,b),0.1);
    }
    float add(long a,float b){
        return a+b;
    }
}