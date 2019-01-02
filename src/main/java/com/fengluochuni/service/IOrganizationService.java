package com.fengluochuni.service;

import com.baomidou.mybatisplus.service.IService;
import com.fengluochuni.commons.result.Tree;
import com.fengluochuni.model.Organization;

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