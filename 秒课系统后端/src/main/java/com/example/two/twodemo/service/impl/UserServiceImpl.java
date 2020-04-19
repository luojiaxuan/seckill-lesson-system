package com.example.two.twodemo.service.impl;

import com.example.two.twodemo.VO.UserVO;
import com.example.two.twodemo.model.User;
import com.example.two.twodemo.redis.UserRedis;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.two.twodemo.repository.UserRepository;
import com.example.two.twodemo.service.UserService;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRedis userRedis;
    @Override
    public User regist(User user) {
        return userRepository.saveAndFlush(user);
    }
    @Override
    public User getUser(String username){
        //UserVO userVO= new UserVO();
        User user = userRedis.get(username);

        if(user==null){
            user=userRepository.findByUsername(username);
            if(user!=null){
                userRedis.put(user.getUsername(),user,-1);
            }else{
                return null;
            }
        }
        //BeanUtils.copyProperties(user,userVO);
        return user;
    }

    @Override
    public void saveUserToRedisByToken(User dbUser,String token){
        //User  user = new User();
        //BeanUtils.copyProperties(dbUser,user);
        userRedis.put(token,dbUser,3600);
    }

    @Override
    public Object getUserFromRedisByToken(String s) {
        return userRedis.get(s);
    }
}
