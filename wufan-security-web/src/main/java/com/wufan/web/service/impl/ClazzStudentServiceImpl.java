package com.wufan.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufan.web.entities.ClazzStudent;
import com.wufan.web.mapper.ClazzStudentMapper;
import com.wufan.web.service.ClazzStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wufan
 * @date 2020/4/14 0014 19:07
 */
@Service
public class ClazzStudentServiceImpl extends ServiceImpl<ClazzStudentMapper, ClazzStudent> implements ClazzStudentService {

    @Override
    public int updateBatch(List<ClazzStudent> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<ClazzStudent> list) {
        return baseMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<ClazzStudent> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public List<ClazzStudent> getByStudentId(String studentId) {
        return baseMapper.getByStudentId(studentId);
    }
}

