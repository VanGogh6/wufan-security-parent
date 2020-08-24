package com.wufan.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wufan.web.entities.MajorClazz;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/** 
 * @author wufan
 * @date 2020/4/21 0021 13:32
 */  
@Mapper
public interface MajorClazzMapper extends BaseMapper<MajorClazz> {
    int updateBatch(List<MajorClazz> list);

    int updateBatchSelective(List<MajorClazz> list);

    int batchInsert(@Param("list") List<MajorClazz> list);
}