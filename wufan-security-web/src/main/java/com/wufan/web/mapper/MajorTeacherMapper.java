package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wufan.web.entities.MajorTeacher;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/** 
 * @author wufan
 * @date 2020/4/14 0014 13:22
 */  
@Mapper
public interface MajorTeacherMapper extends BaseMapper<MajorTeacher> {
    int updateBatch(List<MajorTeacher> list);

    int updateBatchSelective(List<MajorTeacher> list);

    int batchInsert(@Param("list") List<MajorTeacher> list);
}