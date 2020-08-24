package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wufan.web.entities.SubjectDesign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * @author wufan
 * @date 2020/4/13 0013 14:52
 */  
@Mapper
public interface SubjectDesignMapper extends BaseMapper<SubjectDesign> {
    int updateBatch(List<SubjectDesign> list);

    int updateBatchSelective(List<SubjectDesign> list);

    int batchInsert(@Param("list") List<SubjectDesign> list);

    SubjectDesign getBySubjectIdAndDesignId(@Param("subjectId")String subjectId,@Param("designId") String designId);
}