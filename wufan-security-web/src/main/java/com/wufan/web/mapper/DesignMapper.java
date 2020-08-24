package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.web.entities.Design;
import com.wufan.web.entities.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * @author wufan
 * @date 2020/4/11 0011 3:33
 */  
@Mapper
public interface DesignMapper extends BaseMapper<Design> {
    int updateBatch(List<Design> list);

    int updateBatchSelective(List<Design> list);

    int batchInsert(@Param("list") List<Design> list);

    IPage<Design> selectPage(Page<Design> page,@Param("d")  Design design);


    List<Design> getDesignList(@Param("subjectId") String subjectId);

    IPage<Design> getPage(Page<Design> page,@Param("de") Design design,@Param("teacher") SysUser teacher);

    List<Design> getDesignListByStudentId(@Param("studentId") String studentId);
}