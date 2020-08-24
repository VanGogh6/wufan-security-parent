package com.wufan.web.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ok
 * @author wufan
 * @date 2020/4/11 0011 3:33
 */
@ApiModel(value="com-wufan-web-entities-Design")
@Data
@TableName(value = "design")
public class Design implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键")
    private String id;

    /**
     * 课程设计编号
     */
    @TableField(value = "`no`")
    @ApiModelProperty(value="课程设计编号")
    private String no;

    /**
     * 课程设计名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="课程设计名称")
    private String name;

    /**
     * 课程设计描述 teacher_id
     */
    @TableField(value = "`describe`")
    @ApiModelProperty(value="课程设计描述")
    private String describe;

    /**
     * 是否被删除，0未删除，1删除
     */
    @TableField(value = "deleted")
    @ApiModelProperty(value="是否被删除，0未删除，1删除")
    private Integer deleted;

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    private Date createTime;

    @TableField(value = "update_time")
    @ApiModelProperty(value="")
    private Date updateTime;

    private static final long serialVersionUID = 1L;


    /**
     * 存放选择了该课程设计的学生
     */
    @TableField(exist = false)
    private List<SysUser> students= Lists.newArrayList();

    /**
     * 所属学科
     */
    @TableField(exist = false)
    private Subject subject;

    /**
     * 标识那位老师创建的
     */
    @TableField(exist = false)
    private SysUser teacher;

    /**
     * subjectDesignId
     */
    @TableField(exist = false)
    private String subjectDesignId;

}