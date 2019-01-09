package com.fengluochuni.scaffold.service.test.impl;

import com.fengluochuni.scaffold.mapper.auth.UserMapper;
import com.fengluochuni.scaffold.model.auth.User;
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
