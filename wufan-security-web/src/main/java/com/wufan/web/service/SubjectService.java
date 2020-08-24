package com.wufan.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wufan.web.entities.Subject;

import java.util.List;
    /** 
 * @author wufan
 * @date 2020/4/11 0011 3:33
 */  
public interface SubjectService extends IService<Subject>{


    int updateBatch(List<Subject> list);

    int updateBatchSelective(List<Subject> list);

    int batchInsert(List<Subject> list);

    IPage<Subject> selectPage(Page<Subject> page, Subject subject);

    List<Subject> getSubjectList(String majorId);

    List<Subject> getSubjectListByUsername(String username);

    Subject getSubjectByDesignId(String designId);
}
