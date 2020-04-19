package com.example.two.twodemo.controller;

import com.example.two.twodemo.model.User;
import com.example.two.twodemo.service.UserService;
import com.example.two.twodemo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Random;

@Controller
public class RegisterController {
    @Autowired
    public UserService userService;
    @RequestMapping(value="/reg",method = RequestMethod.GET)
    public ModelAndView toRegister(ModelMap model){
        User user = new User();
        return new ModelAndView("register").addObject(user);
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute(value="user")@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("register");
        }
        user.setId(2018);
        //生成一个16位的随机数
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = stringBuilder.length();
        if(len<16){
            for(int i=0;i<16-len;i++){
                stringBuilder.append("0");
            }
        }
        //设置盐
        user.setSalt(stringBuilder.toString());
        //根据盐算密码
        String newpassword = MD5Util.backtToDb(user.getPassword(),user.getSalt());
        user.setPassword(newpassword);
        //注册一个user
        User newuser = userService.regist(user);
        if(newuser!=null) return new ModelAndView("register");
        else return new ModelAndView("register");
    }
}
