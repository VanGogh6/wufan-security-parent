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
 * @date 2020/4/13 0013 14:52
 */  
@ApiModel(value="com-wufan-web-entities-SubjectDesign")
@Data
@TableName(value = "subject_design")
public class SubjectDesign implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键")
    private String id;

    @TableField(value = "subject_id")
    @ApiModelProperty(value="")
    private String subjectId;

    @TableField(value = "design_id")
    @ApiModelProperty(value="")
    private String designId;

    private static final long serialVersionUID = 1L;

    public SubjectDesign(String id, String subjectId, String designId) {
        this.id = id;
        this.subjectId = subjectId;
        this.designId = designId;
    }

}