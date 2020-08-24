package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wufan.web.entities.ClazzTeacher;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/** 
 * @author wufan
 * @date 2020/4/13 0013 15:55
 */  
@Mapper
public interface ClazzTeacherMapper extends BaseMapper<ClazzTeacher> {
    int updateBatch(List<ClazzTeacher> list);

    int updateBatchSelective(List<ClazzTeacher> list);

    int batchInsert(@Param("list") List<ClazzTeacher> list);
}