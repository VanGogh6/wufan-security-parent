package com.wufan.web.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.wufan.web.mapper.MajorTeacherMapper;
import com.wufan.web.entities.MajorTeacher;
import com.wufan.web.service.MajorTeacherService;
/** 
 * @author wufan
 * @date 2020/4/14 0014 13:22
 */  
@Service
public class MajorTeacherServiceImpl extends ServiceImpl<MajorTeacherMapper, MajorTeacher> implements MajorTeacherService{

    @Override
    public int updateBatch(List<MajorTeacher> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<MajorTeacher> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<MajorTeacher> list) {
        return baseMapper.batchInsert(list);
    }
}
