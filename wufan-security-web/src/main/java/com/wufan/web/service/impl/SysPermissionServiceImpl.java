package com.wufan.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufan.web.entities.SysPermission;
import com.wufan.web.mapper.SysPermissionMapper;
import com.wufan.web.service.SysPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    
    
    @Override
    public List<SysPermission> findByUserId(Long userId) {
        if(userId == null) {
            return null;
        }
        List<SysPermission> permissionList = baseMapper.selectPermissionByUserId(userId);
        // 如果没有权限，则将集合中的数据null移除
//        permissionList.remove(null);
        return permissionList;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        // 1. 删除当前id的权限
        baseMapper.deleteById(id);
        // 2. 删除parent_id = id 的权限, 删除当前点击的子权限
        LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper();
        //delete from sys_permission where parent_id = #{id};
        queryWrapper.eq(SysPermission::getParentId, id);
        baseMapper.delete(queryWrapper);
        return true;
    }

}
