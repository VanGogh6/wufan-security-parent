package com.wufan;

import com.wufan.web.entities.SysPermission;
import com.wufan.web.entities.SysRole;
import com.wufan.web.entities.SysUser;
import com.wufan.web.service.SysPermissionService;
import com.wufan.web.service.SysRoleService;
import com.wufan.web.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWebApplication {

    @Autowired
    SysUserService sysUserService;


    @Test
    public void testSysUser() {
//        List<SysUser> list = sysUserService.list();
//        System.out.println("list:" + list);

        SysUser user = sysUserService.findByUsername("admin");
        System.out.println("user: " + user);
    }


    @Autowired
    SysRoleService sysRoleService;

    @Test
    public void testSysRole(){
        SysRole role = sysRoleService.getById(9);
        System.out.println("role:" + role);
    }

    @Autowired
    SysPermissionService sysPermissionService;

    @Test
    public void testSysPer(){
//        SysPermission permission = sysPermissionService.getById(18);
//        System.out.println(permission);
        List<SysPermission> permissions = sysPermissionService.findByUserId(9L);
        System.out.println("permissions:" + permissions.size());
    }


}
