package com.example.two.twodemo.controller.api;

import com.example.two.twodemo.base.controller.BaseApiController;
import com.example.two.twodemo.base.result.Result;
import com.example.two.twodemo.base.result.ResultCode;
import com.example.two.twodemo.model.User;
import com.example.two.twodemo.service.UserService;
import com.example.two.twodemo.util.MD5Util;
import com.example.two.twodemo.util.UUIDUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;

@RestController
public class LoginApiController extends BaseApiController {
    private static Logger log  = (Logger) LoggerFactory.getLogger(LoginApiController.class);
    @Autowired
    public UserService userService;
    @RequestMapping(value="/login")
    public Result<Object> login(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult, HttpSession session, String code, Model model, HttpServletResponse response){
        log.info("username"+user.getUsername()+";password"+user.getPassword());
        if(bindingResult.hasErrors()){
            return Result.failure();
        }
        User dbUser = userService.getUser(user.getUsername());
        log.info("dbUser:"+dbUser.toString());
        if(dbUser!=null) {
            if (dbUser.getPassword().equals(MD5Util.inputToDb(user.getPassword(), dbUser.getSalt()))) {
                String token = UUIDUtil.getUUID();
                userService.saveUserToRedisByToken(dbUser, token);
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                return Result.success();//Result.success();200 "success"
            } else {
                return Result.failure(ResultCode.USER_LOGIN_ERROR);
            }
        }else{
                return Result.failure(ResultCode.USER_LOGIN_ERROR);
            }
        }
    }
