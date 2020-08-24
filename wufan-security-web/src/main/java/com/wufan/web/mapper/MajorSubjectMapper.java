package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wufan.web.entities.MajorSubject;
import com.wufan.web.entities.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * @author wufan
 * @date 2020/4/21 0021 13:35
 */  
@Mapper
public interface MajorSubjectMapper extends BaseMapper<MajorSubject> {
    int updateBatch(List<MajorSubject> list);

    int updateBatchSelective(List<MajorSubject> list);

    int batchInsert(@Param("list") List<MajorSubject> list);

    List<Subject> getByMajorId(@Param("majorId") String majorId);

    List<Subject> getByStudentId(@Param("studentId") String studentId);
}