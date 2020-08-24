package com.wufan.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wufan.web.entities.ClazzStudent;

import java.util.List;

/**
 * @author wufan
 * @date 2020/4/14 0014 19:07
 */
public interface ClazzStudentService extends IService<ClazzStudent> {


    int updateBatch(List<ClazzStudent> list);

    int updateBatchSelective(List<ClazzStudent> list);

    int batchInsert(List<ClazzStudent> list);

    List<ClazzStudent> getByStudentId(String studentId);
}

