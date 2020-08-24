package com.wufan.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wufan.web.entities.Design;
import com.wufan.web.entities.SysUser;

import java.util.List;

/**
 * @author wufan
 * @date 2020/4/11 0011 3:33
 */  
public interface DesignService extends IService<Design>{


    int updateBatch(List<Design> list);

    int updateBatchSelective(List<Design> list);

    int batchInsert(List<Design> list);

    IPage<Design> selectPage(Page<Design> page, Design design);

    List<Design> getDesignList(String subjectId);

    IPage<Design> getPage(Page<Design> page, Design design, SysUser teacher);

    List<Design> getDesignListByStudentId(String studentId);
}
