package com.wufan.web.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/** 
 * @author wufan
 * @date 2020/4/21 0021 13:32
 */  
@ApiModel(value="com-wufan-web-entities-MajorClazz")
@Data
@TableName(value = "major_clazz")
public class MajorClazz implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private String id;

    @TableField(value = "major_id")
    @ApiModelProperty(value="")
    private String majorId;

    @TableField(value = "clazz_id")
    @ApiModelProperty(value="")
    private String clazzId;

    private static final long serialVersionUID = 1L;
}