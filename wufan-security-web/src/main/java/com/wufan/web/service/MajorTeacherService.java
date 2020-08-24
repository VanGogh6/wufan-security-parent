package com.wufan.web.service;

import java.util.List;
import com.wufan.web.entities.MajorTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
    /** 
 * @author wufan
 * @date 2020/4/14 0014 13:22
 */  
public interface MajorTeacherService extends IService<MajorTeacher>{


    int updateBatch(List<MajorTeacher> list);

    int updateBatchSelective(List<MajorTeacher> list);

    int batchInsert(List<MajorTeacher> list);

}
