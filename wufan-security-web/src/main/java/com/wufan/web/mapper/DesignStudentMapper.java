package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.web.entities.DesignStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * @author wufan
 * @date 2020/4/13 0013 17:09
 */  
@Mapper
public interface DesignStudentMapper extends BaseMapper<DesignStudent> {
    int updateBatch(List<DesignStudent> list);

    int updateBatchSelective(List<DesignStudent> list);

    int batchInsert(@Param("list") List<DesignStudent> list);

    DesignStudent getByDesignId(@Param("designId") String designId,@Param("studentId")String studentId);

    IPage<DesignStudent> getDesignStudentsByDesignId(Page<DesignStudent> page, @Param("designId")String designId,@Param("designNo")String designNo);

    List<DesignStudent> getRecords(@Param("designId") String designId);

    List<DesignStudent>  getByStudentId(@Param("studentId") String studentId);
}