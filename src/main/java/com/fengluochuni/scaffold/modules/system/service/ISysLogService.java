package com.fengluochuni.scaffold.modules.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.fengluochuni.scaffold.commons.result.PageInfo;
import com.fengluochuni.scaffold.modules.system.model.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends IService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);

}