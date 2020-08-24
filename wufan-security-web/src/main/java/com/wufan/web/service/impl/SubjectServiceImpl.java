package com.wufan.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufan.web.entities.Subject;
import com.wufan.web.mapper.SubjectMapper;
import com.wufan.web.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;
/** 
 * @author wufan
 * @date 2020/4/11 0011 3:33
 */  
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService{

    @Override
    public int updateBatch(List<Subject> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<Subject> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<Subject> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public IPage<Subject> selectPage(Page<Subject> page, Subject subject) {
        return baseMapper.findPage(page, subject);
    }

    @Override
    public List<Subject> getSubjectList(String majorId) {
        List<Subject> list= baseMapper.getSubjectList(majorId);
        return list;
    }

    @Override
    public List<Subject> getSubjectListByUsername(String username) {
        List<Subject> list=baseMapper.getSubjectListByUsername(username);
        return list;
    }

    @Override
    public Subject getSubjectByDesignId(String designId) {
        Subject subject=baseMapper.getSubjectByDesignId(designId);
        return subject;
    }
}
