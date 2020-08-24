package com.wufan.web.service;

import java.util.List;
import com.wufan.web.entities.ClazzTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
    /** 
 * @author wufan
 * @date 2020/4/13 0013 15:55
 */  
public interface ClazzTeacherService extends IService<ClazzTeacher>{


    int updateBatch(List<ClazzTeacher> list);

    int updateBatchSelective(List<ClazzTeacher> list);

    int batchInsert(List<ClazzTeacher> list);

}
