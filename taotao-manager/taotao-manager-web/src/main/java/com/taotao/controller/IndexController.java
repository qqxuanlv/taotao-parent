package com.taotao.controller;

import com.taotao.mapper.UserMapper;
import com.taotao.pojo.User;
import com.taotao.service.UserService;
import com.taotao.service.com.taotao.utils.RedisUtil;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

  @Autowired
  private  UserService iUserService;

  @Autowired
  private RedisUtil redisUtil;

    @GetMapping("/hello")
    public String index(){
        return "service/index";
    }



    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @ResponseBody
    @PostMapping("/login")
    public Object Login(User user) {
        if(iUserService.userByUser(user)==null) return "账号或者密码错误";
        return iUserService.userByUser(user);
    }



}
