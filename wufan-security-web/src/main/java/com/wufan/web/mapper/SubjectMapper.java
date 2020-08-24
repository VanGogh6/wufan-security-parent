package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.web.entities.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * @author wufan
 * @date 2020/4/11 0011 3:33
 */  
@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {
    int updateBatch(List<Subject> list);

    int updateBatchSelective(List<Subject> list);

    int batchInsert(@Param("list") List<Subject> list);

    IPage<Subject> findPage(Page<Subject> page,@Param("s") Subject subject);

    List<Subject> getSubjectList(@Param("major_id")String majorId);

    List<Subject> getSubjectListByUsername(@Param("username")String username);

    Subject getSubjectByDesignId(@Param("designId")String designId);
}