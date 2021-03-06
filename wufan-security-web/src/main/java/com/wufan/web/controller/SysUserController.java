package com.wufan.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.base.result.MyResult;
import com.wufan.web.entities.SysRole;
import com.wufan.web.entities.SysUser;
import com.wufan.web.service.SysRoleService;
import com.wufan.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    private static final String HTML_PREFIX = "system/user/";


    @PreAuthorize("hasAuthority('sys:user')")
    @GetMapping(value = {"/", ""}) // /user/  /user
    public String user() {
        return HTML_PREFIX + "user-list";
    }

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询用户列表
     * @param page 分页对象: size, current
     * @param sysUser 查询条件 : username, mobile
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:list')")
    @PostMapping("/page") // /user/page
    @ResponseBody
    public MyResult page(Page<SysUser> page, SysUser sysUser) {
        return MyResult.ok(sysUserService.selectPage(page, sysUser));
    }


    @Autowired
    private SysRoleService sysRoleService;

    @PreAuthorize("hasAnyAuthority('sys:user:add', 'sys:user:edit')")
    @GetMapping(value={"/form", "/form/{id}"})
    public String form(@PathVariable(required = false) Long id, Model model) {
        // 1, 查询用户信息，包含了用户所拥有的角色
        SysUser user = sysUserService.findById(id);
        model.addAttribute("user", user);

        // 2, 查询出所有角色信息 sys_role
        List<SysRole> roleList = sysRoleService.list();
        model.addAttribute("roleList", roleList);

        return HTML_PREFIX + "user-form";
    }

    @PreAuthorize("hasAnyAuthority('sys:user:add', 'sys:user:edit')")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = "")
    public String saveOrUpdate(SysUser sysUser) {
        // 1. 保存到用户表, 要将选择的角色保存到用户角色中间表
        sysUserService.saveOrUpdate(sysUser);
        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('sys:user:delete')")
    @DeleteMapping("/{id}") // /user/{id}
    @ResponseBody
    public MyResult deleteById(@PathVariable Long id) {
        // 假删除，只做更新
        sysUserService.deleteById(id);
        return MyResult.ok();
    }

}
