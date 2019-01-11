package com.fengluochuni.scaffold.modules.system.controller;

import com.fengluochuni.scaffold.commons.result.PageInfo;
import com.fengluochuni.scaffold.modules.system.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 日志管理
 *
 * @author zhixuan.wang
 * @since 2015/10/30 18:06
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;

    @GetMapping("/manager")
    public String manager() {
        return "admin/syslog";
    }

    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Integer page, Integer rows, 
            @RequestParam(value = "sort", defaultValue = "create_time") String sort, 
            @RequestParam(value = "order", defaultValue = "DESC") String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        sysLogService.selectDataGrid(pageInfo);
        return pageInfo;
    }
}
