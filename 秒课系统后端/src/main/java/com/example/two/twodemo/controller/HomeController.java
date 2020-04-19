package com.example.two.twodemo.controller;

import com.example.two.twodemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class HomeController {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String login(Model model, User user){
        log.info("------------"+user.getUsername());
        model.addAttribute("user",user);
        return "home";
    }
}
