package com.example.two.twodemo.service;

import com.example.two.twodemo.VO.UserVO;
import com.example.two.twodemo.model.User;

public interface UserService {
    public User regist(User user);
    public User getUser(String username);

    public void saveUserToRedisByToken(User temp, String token);

    public Object getUserFromRedisByToken(String s);
}
