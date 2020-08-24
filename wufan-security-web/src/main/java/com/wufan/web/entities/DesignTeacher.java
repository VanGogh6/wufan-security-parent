package com.wufan.web.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/** 
 * @author wufan
 * @date 2020/4/13 0013 14:56
 */  
@ApiModel(value="com-wufan-web-entities-DesignTeacher")
@Data
@TableName(value = "design_teacher")
public class DesignTeacher implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键")
    private String id;

    @TableField(value = "teacher_id")
    @ApiModelProperty(value="")
    private String teacherId;

    @TableField(value = "design_id")
    @ApiModelProperty(value="")
    private String designId;

    private static final long serialVersionUID = 1L;

    public DesignTeacher(String id, String teacherId, String designId) {
        this.id = id;
        this.teacherId = teacherId;
        this.designId = designId;
    }
}