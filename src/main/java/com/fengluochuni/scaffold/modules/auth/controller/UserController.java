package com.fengluochuni.scaffold.modules.auth.controller;

import com.fengluochuni.scaffold.commons.base.BaseController;
import com.fengluochuni.scaffold.commons.result.PageInfo;
import com.fengluochuni.scaffold.commons.shiro.PasswordHash;
import com.fengluochuni.scaffold.commons.shiro.ShiroDbRealm;
import com.fengluochuni.scaffold.commons.utils.StringUtils;
import com.fengluochuni.scaffold.modules.auth.model.Role;
import com.fengluochuni.scaffold.modules.auth.model.User;
import com.fengluochuni.scaffold.modules.auth.model.vo.UserVo;
import com.fengluochuni.scaffold.modules.auth.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author zhixuan.wang
 * @since 2015/10/1 14:51
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordHash passwordHash;

    /**
     * 用户管理页
     *
     * @return  页面路径
     */
    @GetMapping("/manager")
    public String manager() {
        return "admin/user/user";
    }

    /**
     * 用户管理列表
     *
     * @param userVo    {UserVo}
     * @param page  页数
     * @param rows  每页条数
     * @param sort  排序字段
     * @param order 排序关键字
     * @return  操作结果Json
     */
    @PostMapping("/dataGrid")
    @ResponseBody
    public Object dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(userVo.getName())) {
            condition.put("name", userVo.getName());
        }
        if (userVo.getOrganizationId() != null) {
            condition.put("organizationId", userVo.getOrganizationId());
        }
        if (userVo.getCreatedateStart() != null) {
            condition.put("startTime", userVo.getCreatedateStart());
        }
        if (userVo.getCreatedateEnd() != null) {
            condition.put("endTime", userVo.getCreatedateEnd());
        }
        pageInfo.setCondition(condition);
        userService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加用户页
     *
     * @return  页面路径
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/user/userAdd";
    }

    /**
     * 添加用户
     *
     * @param userVo {UserVo}
     * @return  操作结果Json
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid UserVo userVo) {
        List<User> list = userService.selectByLoginName(userVo);
        if (list != null && !list.isEmpty()) {
            return renderError("登录名已存在!");
        }
        String salt = StringUtils.getUUId();
        String pwd = passwordHash.toHex(userVo.getPassword(), salt);
        userVo.setSalt(salt);
        userVo.setPassword(pwd);
        userService.insertByVo(userVo);
        return renderSuccess("添加成功");
    }

    /**
     * 编辑用户页
     *
     * @param id    记录ID
     * @param model {Model}
     * @return  页面路径
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Long id) {
        UserVo userVo = userService.selectVoById(id);
        List<Role> rolesList = userVo.getRolesList();
        List<Long> ids = new ArrayList<Long>();
        for (Role role : rolesList) {
            ids.add(role.getId());
        }
        model.addAttribute("roleIds", ids);
        model.addAttribute("user", userVo);
        return "admin/user/userEdit";
    }

    /**
     * 编辑用户
     *
     * @param userVo    {UserVo}
     * @return  操作结果Json
     */
    @RequiresRoles("admin")
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid UserVo userVo) {
        List<User> list = userService.selectByLoginName(userVo);
        if (list != null && !list.isEmpty()) {
            return renderError("登录名已存在!");
        }
        // 更新密码
        if (StringUtils.isNotBlank(userVo.getPassword())) {
            User user = userService.selectById(userVo.getId());
            String salt = user.getSalt();
            String pwd = passwordHash.toHex(userVo.getPassword(), salt);
            userVo.setPassword(pwd);
        }
        userService.updateByVo(userVo);
        return renderSuccess("修改成功！");
    }

    /**
     * 修改密码页
     *
     * @return  页面路径
     */
    @GetMapping("/editPwdPage")
    public String editPwdPage() {
        return "admin/user/userEditPwd";
    }

    @Autowired
    private ShiroDbRealm shiroDbRealm;
    
    /**
     * 修改密码
     *
     * @param oldPwd    旧密码
     * @param pwd   新密码
     * @return  操作结果Json
     */
    @PostMapping("/editUserPwd")
    @ResponseBody
    public Object editUserPwd(String oldPwd, String pwd) {
        User user = userService.selectById(getUserId());
        String salt = user.getSalt();
        if (!user.getPassword().equals(passwordHash.toHex(oldPwd, salt))) {
            return renderError("老密码不正确!");
        }
        // 修改密码时清理用户的缓存
        shiroDbRealm.removeUserCache(user.getLoginName());
        userService.updatePwdByUserId(getUserId(), passwordHash.toHex(pwd, salt));
        return renderSuccess("密码修改成功！");
    }

    /**
     * 删除用户
     *
     * @param id    记录ID
     * @return  操作结果Json
     */
    @RequiresRoles("admin")
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        Long currentUserId = getUserId();
        if (id == currentUserId) {
            return renderError("不可以删除自己！");
        }
        userService.deleteUserById(id);
        return renderSuccess("删除成功！");
    }
}