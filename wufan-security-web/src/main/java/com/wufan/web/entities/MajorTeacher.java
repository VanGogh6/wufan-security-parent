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
 * @date 2020/4/14 0014 13:22
 */  
@ApiModel(value="com-wufan-web-entities-MajorTeacher")
@Data
@TableName(value = "major_teacher")
public class MajorTeacher implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private String id;

    @TableField(value = "major_id")
    @ApiModelProperty(value="")
    private String majorId;

    @TableField(value = "teacher_id")
    @ApiModelProperty(value="")
    private String teacherId;

    private static final long serialVersionUID = 1L;
}