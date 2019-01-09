package com.fengluochuni.scaffold.service.auth;

import com.baomidou.mybatisplus.service.IService;
import com.fengluochuni.scaffold.commons.result.Tree;
import com.fengluochuni.scaffold.model.auth.Organization;

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