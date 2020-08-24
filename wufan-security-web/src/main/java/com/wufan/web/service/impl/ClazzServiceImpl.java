package com.wufan.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufan.web.entities.Clazz;
import com.wufan.web.mapper.ClazzMapper;
import com.wufan.web.service.ClazzService;
import org.springframework.stereotype.Service;

import java.util.List;
/** 
 * @author wufan
 * @date 2020/4/13 0013 15:56
 */  
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService{

    @Override
    public int updateBatch(List<Clazz> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<Clazz> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<Clazz> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public List<Clazz> getClazzByTeacherId(Long teacherId) {
        return baseMapper.getClazzByTeacherId(teacherId);
    }
}
