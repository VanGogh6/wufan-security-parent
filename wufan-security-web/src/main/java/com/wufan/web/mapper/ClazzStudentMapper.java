package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wufan.web.entities.ClazzStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wufan
 * @date 2020/4/15 0015 1:04
 */
@Mapper
public interface ClazzStudentMapper extends BaseMapper<ClazzStudent> {
    int updateBatch(List<ClazzStudent> list);

    int updateBatchSelective(List<ClazzStudent> list);

    int batchInsert(@Param("list") List<ClazzStudent> list);

    List<ClazzStudent> getByStudentId(@Param("studentId") String studentId);
}