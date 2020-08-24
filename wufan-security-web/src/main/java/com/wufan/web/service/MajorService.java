package com.wufan.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wufan.web.entities.Major;

import java.util.List;
    /**
 * @author wufan
 * @date 2020/4/11 0011 3:33
 */  
public interface MajorService extends IService<Major>{


    int updateBatch(List<Major> list);

    int updateBatchSelective(List<Major> list);

    int batchInsert(List<Major> list);

    IPage<Major> selectPage(Page<Major> page, Major major);

        Major getMajorBySubjectId(String subId);

        Major getMajorByTeacherId(String teacherId);
    }
