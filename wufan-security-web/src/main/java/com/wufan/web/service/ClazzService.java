package com.wufan.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wufan.web.entities.Clazz;

import java.util.List;
    /** 
 * @author wufan
 * @date 2020/4/13 0013 15:56
 */  
public interface ClazzService extends IService<Clazz>{


    int updateBatch(List<Clazz> list);

    int updateBatchSelective(List<Clazz> list);

    int batchInsert(List<Clazz> list);

        List<Clazz> getClazzByTeacherId(Long teacherId);
    }
