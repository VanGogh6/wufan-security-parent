package com.wufan.web.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufan.web.mapper.MajorClazzMapper;
import java.util.List;
import com.wufan.web.entities.MajorClazz;
import com.wufan.web.service.MajorClazzService;
/** 
 * @author wufan
 * @date 2020/4/21 0021 13:32
 */  
@Service
public class MajorClazzServiceImpl extends ServiceImpl<MajorClazzMapper, MajorClazz> implements MajorClazzService{

    @Override
    public int updateBatch(List<MajorClazz> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<MajorClazz> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<MajorClazz> list) {
        return baseMapper.batchInsert(list);
    }
}
