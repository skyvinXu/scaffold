package com.fengluochuni.scaffold.modules.auth.service;

import com.baomidou.mybatisplus.service.IService;
import com.fengluochuni.scaffold.commons.result.Tree;
import com.fengluochuni.scaffold.modules.auth.model.Organization;

import java.util.List;

/**
 *
 * Organization 表数据服务层接口
 *
 */
public interface IOrganizationService extends IService<Organization> {

    List<Tree> selectTree();

    List<Organization> selectTreeGrid();

}