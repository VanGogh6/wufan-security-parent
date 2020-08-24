package com.wufan.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wufan.web.entities.SysPermission;

import java.util.List;

/**
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 通过用户id查询所拥有权限
     * @param userId
     * @return
     */
    List<SysPermission> findByUserId(Long userId);


    /**
     * 通过权限id删除权限资源
     * @param id
     * @return
     */
    boolean deleteById(Long id);

}
