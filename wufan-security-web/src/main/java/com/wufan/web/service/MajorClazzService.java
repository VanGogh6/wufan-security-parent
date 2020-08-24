package com.wufan.web.service;

import java.util.List;
import com.wufan.web.entities.MajorClazz;
import com.baomidou.mybatisplus.extension.service.IService;
    /** 
 * @author wufan
 * @date 2020/4/21 0021 13:32
 */  
public interface MajorClazzService extends IService<MajorClazz>{


    int updateBatch(List<MajorClazz> list);

    int updateBatchSelective(List<MajorClazz> list);

    int batchInsert(List<MajorClazz> list);

}
