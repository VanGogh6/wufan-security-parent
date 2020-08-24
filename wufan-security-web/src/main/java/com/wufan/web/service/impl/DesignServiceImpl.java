package com.wufan.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufan.web.entities.Design;
import com.wufan.web.entities.SysUser;
import com.wufan.web.mapper.DesignMapper;
import com.wufan.web.service.DesignService;
import org.springframework.stereotype.Service;

import java.util.List;
/** 
 * @author wufan
 * @date 2020/4/11 0011 3:33
 */  
@Service
public class DesignServiceImpl extends ServiceImpl<DesignMapper, Design> implements DesignService{

    @Override
    public int updateBatch(List<Design> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<Design> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<Design> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public IPage<Design> selectPage(Page<Design> page, Design design) {
        return baseMapper.selectPage(page,design);
    }

    @Override
    public List<Design> getDesignList(String subjectId) {
        List<Design> list=baseMapper.getDesignList(subjectId);
        return list;
    }

    @Override
    public IPage<Design> getPage(Page<Design> page, Design design, SysUser teacher) {
        return baseMapper.getPage(page,design,teacher);
    }

    @Override
    public List<Design> getDesignListByStudentId(String studentId) {
        return baseMapper.getDesignListByStudentId(studentId);
    }


}
