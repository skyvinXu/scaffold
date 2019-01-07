package com.fengluochuni.controller;

import javax.validation.Valid;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fengluochuni.commons.result.PageInfo;
import com.fengluochuni.model.TempYwyMatch;
import com.fengluochuni.service.ITempYwyMatchService;
import com.fengluochuni.commons.base.BaseController;

/**
 * <p>
 * 业务员对应表 前端控制器
 * </p>
 *
 * @author rongsheng.xu
 * @since 2019-01-07
 */
@Controller
@RequestMapping("/tempYwyMatch")
public class TempYwyMatchController extends BaseController {

    @Autowired private ITempYwyMatchService tempYwyMatchService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/tempYwyMatch/tempYwyMatchList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(TempYwyMatch tempYwyMatch, Integer page, Integer rows, String sort,String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        EntityWrapper<TempYwyMatch> ew = new EntityWrapper<TempYwyMatch>(tempYwyMatch);
        ew.setEntity(tempYwyMatch);
        Page<TempYwyMatch> pages = getPage(pageInfo);
        pages = tempYwyMatchService.selectPage(pages, ew);
        pageInfo.setRows(pages.getRecords());
        pageInfo.setTotal(pages.getTotal());
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/tempYwyMatch/tempYwyMatchAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid TempYwyMatch tempYwyMatch) {
        tempYwyMatch.setCreateTime(new Date());
        tempYwyMatch.setUpdateTime(new Date());
        tempYwyMatch.setDeleteFlag(0);
        boolean b = tempYwyMatchService.insert(tempYwyMatch);
        if (b) {
            return renderSuccess("添加成功！");
        } else {
            return renderError("添加失败！");
        }
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        TempYwyMatch tempYwyMatch = new TempYwyMatch();
        tempYwyMatch.setId(id);
        tempYwyMatch.setUpdateTime(new Date());
        tempYwyMatch.setDeleteFlag(1);
        boolean b = tempYwyMatchService.updateById(tempYwyMatch);
        if (b) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
    
    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Long id) {
        TempYwyMatch tempYwyMatch = tempYwyMatchService.selectById(id);
        model.addAttribute("tempYwyMatch", tempYwyMatch);
        return "admin/tempYwyMatch/tempYwyMatchEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid TempYwyMatch tempYwyMatch) {
        tempYwyMatch.setUpdateTime(new Date());
        boolean b = tempYwyMatchService.updateById(tempYwyMatch);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
