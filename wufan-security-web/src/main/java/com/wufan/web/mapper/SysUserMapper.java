package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.web.entities.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 继承 BaseMapper<T> 接口，它提供了很多对 T 表的数据操作方法
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<SysUser> selectPage(Page<SysUser> page,@Param("u") SysUser sysUser);

    /**
     * 通过用户id删除用户角色表中的所有记录
     * @param userId
     * @return
     */
    boolean deleteUserRoleByUserId(@Param("userId") Long userId);

    /**
     * 保存用户角色关系表数据
     * @param userId 用户id
     * @param roleIds 角色ids
     * @return
     */
    boolean saveUserRole(@Param("userId") Long userId,@Param("roleIds") List<Long> roleIds);


    List<SysUser> getStudentList(String designId);

    SysUser getTeacherByDesignId(String designId);

    List<SysUser> getStudentsByClazzId(@Param("clazzId")String clazzId);
}
