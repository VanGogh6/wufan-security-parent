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
 * @date 2020/4/13 0013 15:55
 */  
@ApiModel(value="com-wufan-web-entities-ClazzTeacher")
@Data
@TableName(value = "clazz_teacher")
public class ClazzTeacher implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键")
    private String id;

    /**
     * 关联学院外键
     */
    @TableField(value = "clazz_id")
    @ApiModelProperty(value="关联学院外键")
    private String clazzId;

    /**
     * 关联专业外键
     */
    @TableField(value = "teacher_id")
    @ApiModelProperty(value="关联专业外键")
    private String teacherId;

    private static final long serialVersionUID = 1L;
}