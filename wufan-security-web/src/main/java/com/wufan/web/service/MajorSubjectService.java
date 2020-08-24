package com.wufan.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wufan.web.entities.MajorSubject;
import com.wufan.web.entities.Subject;

import java.util.List;

/** 
 * @author wufan
 * @date 2020/4/21 0021 13:35
 */  
public interface MajorSubjectService extends IService<MajorSubject>{


    int updateBatch(List<MajorSubject> list);

    int updateBatchSelective(List<MajorSubject> list);

    int batchInsert(List<MajorSubject> list);

        List<Subject> getByMajorId(String majorId);

    List<Subject> getByStudentId(String studentId);
}
