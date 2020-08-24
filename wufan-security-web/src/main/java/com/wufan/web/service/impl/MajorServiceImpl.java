package com.wufan.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufan.web.entities.Major;
import com.wufan.web.mapper.MajorMapper;
import com.wufan.web.service.MajorService;
import org.springframework.stereotype.Service;

import java.util.List;
/** 
 * @author wufan
 * @date 2020/4/11 0011 3:33
 */  
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService{

    @Override
    public int updateBatch(List<Major> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<Major> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<Major> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public IPage<Major> selectPage(Page<Major> page, Major major) {
        return baseMapper.selectPage(page,major);
    }

    @Override
    public Major getMajorBySubjectId(String subId) {
        Major major=baseMapper.getMajorBySubjectId(subId);
        return major;
    }

    @Override
    public Major getMajorByTeacherId(String teacherId) {
        return baseMapper.getMajorByTeacherId(teacherId);
    }
}
