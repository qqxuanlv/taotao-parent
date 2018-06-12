package com.taotao.service.impl;

import com.taotao.mapper.UserMapper;
import com.taotao.pojo.User;
import com.taotao.service.UserService;
import com.taotao.service.com.taotao.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

@Service
public class IUserService implements UserService {

    final static Logger logger = LoggerFactory.getLogger(IUserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String, String> userByUser(User user1) {
        System.out.println("service " + user1);

        Map map = redisUtil.getHashEntries(user1.getUsername());


        if(map.isEmpty())
        {


            logger.info("无记录需要从数据库里面查");

            User user = userMapper.selectByUsername(user1);

            if(user==null)
            {
                System.out.println("数据库中账号密码不正确");
                return null;
            }
            else
            {
                System.out.println(user);
                System.out.println("加入redis缓存");
                redisUtil.putAll(user1.getUsername(),RedisUtil.obj2Map(user));
                return redisUtil.obj2Map(user);
            }


        }
        else if(user1.getPassword().equals(map.get("password")))
        {

            System.out.println("有记录并且账号密码正确");
            return map;
        }
        else
        {
            System.out.println("有记录但是密码不正确");
            return null;
        }







    }
}

