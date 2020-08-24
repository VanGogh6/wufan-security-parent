package com.wufan.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufan.web.entities.MajorSubject;
import com.wufan.web.entities.Subject;
import com.wufan.web.mapper.MajorSubjectMapper;
import com.wufan.web.service.MajorSubjectService;
import org.springframework.stereotype.Service;

import java.util.List;
/** 
 * @author wufan
 * @date 2020/4/21 0021 13:35
 */  
@Service
public class MajorSubjectServiceImpl extends ServiceImpl<MajorSubjectMapper, MajorSubject> implements MajorSubjectService{

    @Override
    public int updateBatch(List<MajorSubject> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<MajorSubject> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<MajorSubject> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public List<Subject> getByMajorId(String majorId) {
        return baseMapper.getByMajorId(majorId);
    }

    @Override
    public List<Subject> getByStudentId(String studentId) {
        return baseMapper.getByStudentId(studentId);
    }
}
