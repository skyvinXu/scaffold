package com.fengluochuni.scaffold.modules.auth.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fengluochuni.scaffold.modules.auth.mapper.UserRoleMapper;
import com.fengluochuni.scaffold.modules.auth.model.UserRole;
import com.fengluochuni.scaffold.modules.auth.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 *
 * UserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}