package com.fengluochuni.scaffold.modules.auth.controller;

import com.fengluochuni.scaffold.commons.base.BaseController;
import com.fengluochuni.scaffold.commons.result.PageInfo;
import com.fengluochuni.scaffold.modules.auth.model.Role;
import com.fengluochuni.scaffold.modules.auth.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理
 *
 * @author zhixuan.wang
 * @since 2015/10/1 14:51
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    /**
     * 角色管理页
     *
     * @return  页面路径
     */
    @GetMapping("/manager")
    public String manager() {
        return "admin/role/role";
    }

    /**
     * 角色列表
     *
     * @param page  页数
     * @param rows  每页条数
     * @param sort  排序字段
     * @param order 排序关键字
     * @return  操作结果Json
     */
    @PostMapping("/dataGrid")
    @ResponseBody
    public Object dataGrid(Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        roleService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 角色树
     *
     * @return  操作结果Json
     */
    @PostMapping("/tree")
    @ResponseBody
    public Object tree() {
        return roleService.selectTree();
    }

    /**
     * 添加角色页
     *
     * @return  页面路径
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/role/roleAdd";
    }

    /**
     * 添加权限
     *
     * @param role  {Role}
     * @return  操作结果Json
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Role role) {
        roleService.insert(role);
        return renderSuccess("添加成功！");
    }

    /**
     * 删除权限
     *
     * @param id    记录ID
     * @return  操作结果Json
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        roleService.deleteById(id);
        return renderSuccess("删除成功！");
    }

    /**
     * 编辑权限页
     *
     * @param model {Model}
     * @param id    记录ID
     * @return  页面路径
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Long id) {
        Role role = roleService.selectById(id);
        model.addAttribute("role", role);
        return "admin/role/roleEdit";
    }

    /**
     * 删除权限
     *
     * @param role  {Role}
     * @return  操作结果Json
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Role role) {
        roleService.updateById(role);
        return renderSuccess("编辑成功！");
    }

    /**
     * 授权页面
     *
     * @param id    记录ID
     * @param model {Model}
     * @return  页面路径
     */
    @GetMapping("/grantPage")
    public String grantPage(Model model, Long id) {
        model.addAttribute("id", id);
        return "admin/role/roleGrant";
    }

    /**
     * 授权页面页面根据角色查询资源
     *
     * @param id    记录ID
     * @return  操作结果Json
     */
    @PostMapping("/findResourceIdListByRoleId")
    @ResponseBody
    public Object findResourceByRoleId(Long id) {
        List<Long> resources = roleService.selectResourceIdListByRoleId(id);
        return renderSuccess(resources);
    }

    /**
     * 授权
     *
     * @param id    记录ID
     * @param resourceIds   授权资源ID列表
     * @return  操作结果Json
     */
    @RequiresRoles("admin")
    @PostMapping("/grant")
    @ResponseBody
    public Object grant(Long id, String resourceIds) {
        roleService.updateRoleResource(id, resourceIds);
        return renderSuccess("授权成功！");
    }

}
