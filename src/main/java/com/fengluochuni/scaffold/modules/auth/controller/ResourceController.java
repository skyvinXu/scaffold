package com.fengluochuni.scaffold.modules.auth.controller;

import com.fengluochuni.scaffold.commons.base.BaseController;
import com.fengluochuni.scaffold.commons.shiro.ShiroUser;
import com.fengluochuni.scaffold.modules.auth.model.Resource;
import com.fengluochuni.scaffold.modules.auth.service.IResourceService;
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
 * 资源管理
 *
 * @author zhixuan.wang
 * @since 2015/10/1 14:51
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;

    /**
     * 菜单树
     *
     * @return  操作结果Json
     */
    @PostMapping("/tree")
    @ResponseBody
    public Object tree() {
        ShiroUser shiroUser = getShiroUser();
        return resourceService.selectTree(shiroUser);
    }

    /**
     * 资源管理页
     *
     * @return  页面路径
     */
    @GetMapping("/manager")
    public String manager() {
        return "admin/resource/resource";
    }

    /**
     * 资源管理列表
     *
     * @return  操作结果Json
     */
    @PostMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid() {
        return resourceService.selectAll();
    }

    /**
     * 添加资源页
     *
     * @return  页面路径
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/resource/resourceAdd";
    }

    /**
     * 添加资源
     *
     * @param resource  {Resource}
     * @return  操作结果Json
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Resource resource) {
        resource.setCreateTime(new Date());
        // 选择菜单时将openMode设置为null
        Integer type = resource.getResourceType();
        if (null != type && type == 0) {
            resource.setOpenMode(null);
        }
        resourceService.insert(resource);
        return renderSuccess("添加成功！");
    }

    /**
     * 查询所有的菜单
     *
     * @return 操作结果Json
     */
    @PostMapping("/allTree")
    @ResponseBody
    public Object allMenu() {
        return resourceService.selectAllMenu();
    }

    /**
     * 查询所有的资源tree
     *
     * @return 操作结果Json
     */
    @PostMapping("/allTrees")
    @ResponseBody
    public Object allTree() {
        return resourceService.selectAllTree();
    }

    /**
     * 编辑资源页
     *
     * @param model {Model}
     * @param id    记录ID
     * @return  页面路径
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Long id) {
        Resource resource = resourceService.selectById(id);
        model.addAttribute("resource", resource);
        return "admin/resource/resourceEdit";
    }

    /**
     * 编辑资源
     *
     * @param resource  {Resource}
     * @return  操作结果Json
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Resource resource) {
        resourceService.updateById(resource);
        return renderSuccess("编辑成功！");
    }

    /**
     * 删除资源
     *
     * @param id    记录ID
     * @return  操作结果Json
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        resourceService.deleteById(id);
        return renderSuccess("删除成功！");
    }

}
