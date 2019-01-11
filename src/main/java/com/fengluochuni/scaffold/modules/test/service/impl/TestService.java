package com.fengluochuni.scaffold.modules.test.service.impl;

import com.fengluochuni.scaffold.modules.auth.mapper.UserMapper;
import com.fengluochuni.scaffold.modules.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class TestService {
    @Autowired
    private UserMapper userMapper;
	
    @Cacheable(value = "hour", key = "#id")
	public User selectById(Serializable id) {
		return userMapper.selectById(id);
	}
}
