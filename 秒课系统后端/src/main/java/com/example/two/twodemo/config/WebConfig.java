package com.example.two.twodemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;


@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Resource
    UserArgumentResolver userArgumentResolver;

//    @Override
//    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
//        argumentResolvers.add(userArgumentResolver);
//    }


    public void addResourceHandlers (ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        super.addResourceHandlers(registry);
    }
}
