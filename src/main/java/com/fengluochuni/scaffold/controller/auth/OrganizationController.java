package com.fengluochuni.scaffold.controller.auth;

import com.fengluochuni.scaffold.commons.base.BaseController;
import com.fengluochuni.scaffold.model.auth.Organization;
import com.fengluochuni.scaffold.service.auth.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;

/**
 * 部门管理
 *
 * @author zhixuan.wang
 * @since 2015/10/1 14:51
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

    @Autowired
    private IOrganizationService organizationService;

    /**
     * 部门管理主页
     *
     * @return
     */
    @GetMapping(value = "/manager")
    public String manager() {
        return "admin/organization/organization";
    }

    /**
     * 部门资源树
     *
     * @return
     */
    @PostMapping(value = "/tree")
    @ResponseBody
    public Object tree() {
        return organizationService.selectTree();
    }

    /**
     * 部门列表
     *
     * @return
     */
    @PostMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid() {
        return organizationService.selectTreeGrid();
    }

    /**
     * 添加部门页
     *
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/organization/organizationAdd";
    }

    /**
     * 添加部门
     *
     * @param organization
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Organization organization) {
        organization.setCreateTime(new Date());
        organizationService.insert(organization);
        return renderSuccess("添加成功！");
    }

    /**
     * 编辑部门页
     *
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Long id) {
        Organization organization = organizationService.selectById(id);
        model.addAttribute("organization", organization);
        return "admin/organization/organizationEdit";
    }

    /**
     * 编辑部门
     *
     * @param organization
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Organization organization) {
        organizationService.updateById(organization);
        return renderSuccess("编辑成功！");
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        organizationService.deleteById(id);
        return renderSuccess("删除成功！");
    }
}