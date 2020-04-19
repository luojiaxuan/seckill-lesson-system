package com.example.two.twodemo.controller;

import com.example.two.twodemo.VO.UserVO;
import com.example.two.twodemo.model.User;
import com.example.two.twodemo.service.UserService;
import com.example.two.twodemo.util.MD5Util;
import com.example.two.twodemo.util.UUIDUtil;
import com.example.two.twodemo.util.ValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;
import sun.security.provider.MD5;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    public UserService userService;
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("title","登陆页面");
        return "login";
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult bindingResult, HttpSession session, Model model,String code,HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login";
        }
        String sessionCode = (String) session.getAttribute("code");
        if(code.length()!=5) return "login";
        if(!StringUtils.equalsIgnoreCase(code,sessionCode)){
            model.addAttribute("message","验证码不匹配");
            return "login";
        }
        logger.info("--------"+user.getPassword());
        //检查用户是否存在
        User temp = userService.getUser(user.getUsername());
        logger.info("-----------------User.salt"+temp.getSalt());
        //密码是否一致
        if(temp!=null){
            String inputPassword = MD5Util.backtToDb(user.getPassword(),temp.getSalt());
            logger.info("------------------inputPassword"+inputPassword);
            if(temp.getPassword().equals(inputPassword)){
                logger.info("---------------"+temp.getPassword());
                //保存session
                //session.setAttribute("user",temp);
                String token = UUIDUtil.getUUID();
                userService.saveUserToRedisByToken(temp,token);
                Cookie cookie = new Cookie("token",token);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                return "redirect:/home";
            }else{
                return "login";
            }
        }
        else{
            return "login";
        }
    }

    /**
     * 响应验证码界面
     * */
    @RequestMapping(value="/validateCode")
    public String validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //设置响应格式位图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);

        HttpSession session = request.getSession();
        ValidateCode vCode = new ValidateCode(180,34,5,100);
        session.setAttribute("code",vCode.getCode());
        vCode.write(response.getOutputStream());
        return null;
    }
}
