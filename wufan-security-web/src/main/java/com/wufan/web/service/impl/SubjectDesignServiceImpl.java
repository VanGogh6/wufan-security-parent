package com.wufan.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufan.web.entities.SubjectDesign;
import com.wufan.web.mapper.SubjectDesignMapper;
import com.wufan.web.service.SubjectDesignService;
import org.springframework.stereotype.Service;

import java.util.List;
/** 
 * @author wufan
 * @date 2020/4/13 0013 14:52
 */  
@Service
public class SubjectDesignServiceImpl extends ServiceImpl<SubjectDesignMapper, SubjectDesign> implements SubjectDesignService{

    @Override
    public int updateBatch(List<SubjectDesign> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<SubjectDesign> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<SubjectDesign> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public SubjectDesign getBySubjectIdAndDesignId(String subjectId, String designId) {
        return baseMapper.getBySubjectIdAndDesignId(subjectId,designId);
    }
}
