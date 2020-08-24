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
@ApiModel(value="com-wufan-web-entities-Subject")
@Data
@TableName(value = "subject")
public class Subject implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键")
    private String id;

    /**
     * 学科编号
     */
    @TableField(value = "no")
    @ApiModelProperty(value="学科编号")
    private String no;

    /**
     * 学科名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="学科名称")
    private String name;

    /**
     * 学院负责人ID
     */
    @TableField(value = "manager_id")
    @ApiModelProperty(value="学院负责人ID")
    private String managerId;

    /**
     * 是否被删除，0未删除，1删除
     */
    @TableField(value = "deleted")
    @ApiModelProperty(value="是否被删除，0未删除，1删除")
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;


    /**
     * 该学科负责人
     */
    @TableField(exist = false)
    private String userName;

    private static final long serialVersionUID = 1L;

    /**
     * 一个学科下有多个课程设计（比如期中课程设计，期末课程设计）
     * 查询该学科时查询该学科下的所有课程设计，存入此处
     */
    @TableField(exist = false)
    private List<Design> designs= Lists.newArrayList();

    /**
     * 所属专业
     */
    @TableField(exist = false)
    private Major major;


}