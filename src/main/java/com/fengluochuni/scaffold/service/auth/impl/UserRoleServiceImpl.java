package com.fengluochuni.scaffold.service.auth.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fengluochuni.scaffold.mapper.auth.UserRoleMapper;
import com.fengluochuni.scaffold.model.auth.UserRole;
import com.fengluochuni.scaffold.service.auth.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 *
 * UserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}