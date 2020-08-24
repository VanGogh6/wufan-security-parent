package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wufan.web.entities.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wufan
 * @date 2020/4/11 0011 2:37m
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {


    List<SysPermission> selectPermissionByUserId(@Param("userId") Long userId);

    List<SysPermission> findByRoleId(@Param("roleId") Long roleId);


}
