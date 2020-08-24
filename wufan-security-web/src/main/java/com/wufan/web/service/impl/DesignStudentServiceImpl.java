package com.wufan.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufan.web.entities.DesignStudent;
import com.wufan.web.mapper.DesignStudentMapper;
import com.wufan.web.service.DesignStudentService;
import org.springframework.stereotype.Service;

import java.util.List;
/** 
 * @author wufan
 * @date 2020/4/13 0013 17:09
 */  
@Service
public class DesignStudentServiceImpl extends ServiceImpl<DesignStudentMapper, DesignStudent> implements DesignStudentService{

    @Override
    public int updateBatch(List<DesignStudent> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<DesignStudent> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<DesignStudent> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public DesignStudent getByDesignId(String designId,String studentId) {
        return baseMapper.getByDesignId(designId,studentId);
    }

    @Override
    public IPage<DesignStudent> getDesignStudentsByDesignId(Page<DesignStudent> page, String id,String designNo) {
        return baseMapper.getDesignStudentsByDesignId(page,id,designNo);
    }

    @Override
    public boolean deleteByDesignId(String designId) {

        QueryWrapper<DesignStudent> wrapper=new QueryWrapper<>();
        //design_id为数据库表中的字段，容易出错
        wrapper.eq("design_id",designId);
//        //对比上面这儿不容易出错
//        LambdaQueryWrapper<DesignStudent> queryWrapper=new LambdaQueryWrapper<>();
//        queryWrapper.eq(DesignStudent::getDesignId,designId);

        int delete = baseMapper.delete(wrapper);
        return delete==1;
    }

    @Override
    public List<DesignStudent> getRecords(String designId) {
        return baseMapper.getRecords(designId);
    }

    @Override
    public List<DesignStudent>  getByStudentId(String studentId) {
        return baseMapper.getByStudentId(studentId);
    }
}
