package com.fengluochuni.service;

import com.baomidou.mybatisplus.service.IService;
import com.fengluochuni.commons.result.Tree;
import com.fengluochuni.commons.shiro.ShiroUser;
import com.fengluochuni.model.Resource;

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