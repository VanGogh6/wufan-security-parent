package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wufan.web.entities.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * @author wufan
 * @date 2020/4/13 0013 15:56
 */  
@Mapper
public interface ClazzMapper extends BaseMapper<Clazz> {
    int updateBatch(List<Clazz> list);

    int updateBatchSelective(List<Clazz> list);

    int batchInsert(@Param("list") List<Clazz> list);

    /**
     * 根据老师id查询老师所教的班级
     * @param teacherId
     * @return
     */
    List<Clazz> getClazzByTeacherId(@Param("teacherId") Long teacherId);
}