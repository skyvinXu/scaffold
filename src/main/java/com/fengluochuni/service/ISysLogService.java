package com.fengluochuni.service;

import com.baomidou.mybatisplus.service.IService;
import com.fengluochuni.commons.result.PageInfo;
import com.fengluochuni.model.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends IService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);

}