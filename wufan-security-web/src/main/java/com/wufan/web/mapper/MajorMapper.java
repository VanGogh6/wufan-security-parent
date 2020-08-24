package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.web.entities.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * @author wufan
 * @date 2020/4/11 0011 3:33
 */  
@Mapper
public interface MajorMapper extends BaseMapper<Major> {
    int updateBatch(List<Major> list);

    int updateBatchSelective(List<Major> list);

    int batchInsert(@Param("list") List<Major> list);

    IPage<Major> selectPage(Page<Major> page, @Param("m")  Major major);

    Major getMajorBySubjectId(@Param("subId")String subId);

    Major getMajorByTeacherId(@Param("teacherId")String teacherId);
}