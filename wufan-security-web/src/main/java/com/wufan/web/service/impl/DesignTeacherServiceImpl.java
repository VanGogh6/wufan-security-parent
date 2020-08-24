package com.wufan.web.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.wufan.web.mapper.DesignTeacherMapper;
import com.wufan.web.entities.DesignTeacher;
import com.wufan.web.service.DesignTeacherService;
/** 
 * @author wufan
 * @date 2020/4/13 0013 14:56
 */  
@Service
public class DesignTeacherServiceImpl extends ServiceImpl<DesignTeacherMapper, DesignTeacher> implements DesignTeacherService{

    @Override
    public int updateBatch(List<DesignTeacher> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<DesignTeacher> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<DesignTeacher> list) {
        return baseMapper.batchInsert(list);
    }
}
