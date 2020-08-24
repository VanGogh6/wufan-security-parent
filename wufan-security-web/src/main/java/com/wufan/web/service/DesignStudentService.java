package com.wufan.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wufan.web.entities.DesignStudent;

import java.util.List;
    /** 
 * @author wufan
 * @date 2020/4/13 0013 17:09
 */  
public interface DesignStudentService extends IService<DesignStudent>{


    int updateBatch(List<DesignStudent> list);

    int updateBatchSelective(List<DesignStudent> list);

    int batchInsert(List<DesignStudent> list);

        DesignStudent getByDesignId(String designId,String studentId);

        /**
         * 通过课程设计id查询出他的集合
         * @param id
         * @return
         */
        IPage<DesignStudent> getDesignStudentsByDesignId(Page<DesignStudent> page, String id,String designNo);

        boolean deleteByDesignId(String id);

        List<DesignStudent> getRecords(String designId);

        List<DesignStudent>  getByStudentId(String studentId);
    }
