package com.wufan.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wufan.web.entities.SubjectDesign;

import java.util.List;
    /** 
 * @author wufan
 * @date 2020/4/13 0013 14:52
 */  
public interface SubjectDesignService extends IService<SubjectDesign>{


    int updateBatch(List<SubjectDesign> list);

    int updateBatchSelective(List<SubjectDesign> list);

    int batchInsert(List<SubjectDesign> list);

        SubjectDesign getBySubjectIdAndDesignId(String subjectId, String designId);
    }
