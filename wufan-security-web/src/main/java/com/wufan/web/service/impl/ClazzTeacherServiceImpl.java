package com.wufan.web.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.wufan.web.mapper.ClazzTeacherMapper;
import com.wufan.web.entities.ClazzTeacher;
import com.wufan.web.service.ClazzTeacherService;
/** 
 * @author wufan
 * @date 2020/4/13 0013 15:55
 */  
@Service
public class ClazzTeacherServiceImpl extends ServiceImpl<ClazzTeacherMapper, ClazzTeacher> implements ClazzTeacherService{

    @Override
    public int updateBatch(List<ClazzTeacher> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<ClazzTeacher> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<ClazzTeacher> list) {
        return baseMapper.batchInsert(list);
    }
}
