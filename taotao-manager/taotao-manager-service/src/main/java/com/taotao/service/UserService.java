package com.taotao.service;

import com.taotao.pojo.User;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public interface UserService {


  Map<String,String> userByUser(User  user);
}


