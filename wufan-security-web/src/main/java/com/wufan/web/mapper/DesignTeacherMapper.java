package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wufan.web.entities.DesignTeacher;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/** 
 * @author wufan
 * @date 2020/4/13 0013 14:56
 */  
@Mapper
public interface DesignTeacherMapper extends BaseMapper<DesignTeacher> {
    int updateBatch(List<DesignTeacher> list);

    int updateBatchSelective(List<DesignTeacher> list);

    int batchInsert(@Param("list") List<DesignTeacher> list);
}