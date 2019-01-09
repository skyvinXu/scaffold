package com.fengluochuni.scaffold.service.auth;

import com.baomidou.mybatisplus.service.IService;
import com.fengluochuni.scaffold.commons.result.Tree;
import com.fengluochuni.scaffold.commons.shiro.ShiroUser;
import com.fengluochuni.scaffold.model.auth.Resource;

import java.util.List;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IResourceService extends IService<Resource> {

    List<Resource> selectAll();

    List<Tree> selectAllMenu();

    List<Tree> selectAllTree();

    List<Tree> selectTree(ShiroUser shiroUser);

}