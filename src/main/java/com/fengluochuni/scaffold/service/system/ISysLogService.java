package com.fengluochuni.scaffold.service.system;

import com.baomidou.mybatisplus.service.IService;
import com.fengluochuni.scaffold.commons.result.PageInfo;
import com.fengluochuni.scaffold.model.system.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends IService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);

}