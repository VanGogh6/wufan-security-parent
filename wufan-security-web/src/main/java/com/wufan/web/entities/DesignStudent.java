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
 * @date 2020/4/13 0013 17:09
 */  
@ApiModel(value="com-wufan-web-entities-DesignStudent")
@Data
@TableName(value = "design_student")
public class DesignStudent implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键")
    private String id;

    /**
     * 课程id
     */
    @TableField(value = "design_id")
    @ApiModelProperty(value="课程id")
    private String designId;

    /**
     * 学生id
     */
    @TableField(value = "student_id")
    @ApiModelProperty(value="学生id")
    private String studentId;

    /**
     * 默认0为未上交
     */
    @TableField(value = "upload")
    @ApiModelProperty(value="默认0为未上交")
    private Integer upload;

    /**
     * 默认0为未上交
     */
    @TableField(value = "url")
    @ApiModelProperty(value="url")
    private String url;


    /**
     * 上传时间
     */
    @TableField(value = "upload_Time")
    @ApiModelProperty(value="上传时间")
    private String uploadTime;

    @TableField(exist = false)
    private Design design;

    @TableField(exist = false)
    private SysUser student;

    @TableField(exist = false)
    private Clazz clazz;


    private static final long serialVersionUID = 1L;

    public DesignStudent(String id, String designId, String studentId) {
        this.id = id;
        this.designId = designId;
        this.studentId = studentId;
    }

    public DesignStudent(String id, Integer upload, String uploadTime) {
        this.id = id;
        this.upload = upload;
        this.uploadTime = uploadTime;
    }

    public DesignStudent(String id, String designId, String studentId, Integer upload, String uploadTime) {
        this.id = id;
        this.designId = designId;
        this.studentId = studentId;
        this.upload = upload;
        this.uploadTime = uploadTime;
    }

    public DesignStudent() {

    }

    public DesignStudent(String designId) {
        this.designId = designId;
    }
}