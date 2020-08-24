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
 * @date 2020/4/13 0013 15:56
 */  
@ApiModel(value="com-wufan-web-entities-Clazz")
@Data
@TableName(value = "clazz")
public class Clazz implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键")
    private String id;

    /**
     * 班级编号
     */
    @TableField(value = "no")
    @ApiModelProperty(value="班级编号")
    private String no;

    /**
     * 班级名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="班级名称")
    private String name;

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
     * 是否删除（0：未删除  1：被删除）
     */
    @TableField(value = "deleted")
    @ApiModelProperty(value="是否删除（0：未删除  1：被删除）")
    private Byte deleted;

    private static final long serialVersionUID = 1L;

    /**
     * 班级下的所有学生
     */
    @TableField(exist = false)
    List<SysUser> students= Lists.newArrayList();

}