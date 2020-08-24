package com.wufan.web.service;

import java.util.List;
import com.wufan.web.entities.DesignTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
    /** 
 * @author wufan
 * @date 2020/4/13 0013 14:56
 */  
public interface DesignTeacherService extends IService<DesignTeacher>{


    int updateBatch(List<DesignTeacher> list);

    int updateBatchSelective(List<DesignTeacher> list);

    int batchInsert(List<DesignTeacher> list);

}
