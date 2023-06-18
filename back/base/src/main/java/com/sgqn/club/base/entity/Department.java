package com.sgqn.club.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgqn.club.base.bean.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wspstart
 * @since 2023-06-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_department")
public class Department extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    @TableField("name")
    private String name;

    /**
     * 部门描述
     */
    @TableField("description")
    private String description;

    /**
     * 所属社团ID
     */
    @TableField("club_id")
    private Integer clubId;

    /**
     * 部门成立时间
     */
    @TableField("established_date")
    private Date establishedDate;

}
