package com.sgqn.club.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgqn.club.base.bean.BaseEntity;
import lombok.*;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wspstart
 * @since 2023-06-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_club")
public class Club extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 社团名称
     */
    @TableField("name")
    private String name;

    /**
     * 描述（可以是社团简介等信息）
     */
    @TableField("description")
    private String description;

    /**
     * 成立时间
     */
    @TableField("established_date")
    private Date establishedDate;

    /**
     * 当前人数
     */
    @TableField("cur_number_people")
    private Integer curNumberPeople;

    /**
     * 所属学院
     */
    @TableField("affiliated_college")
    private String affiliatedCollege;

    /**
     * 社团联系邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 前端排序字段
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 当前届数
     */
    @TableField("cur_session")
    private String curSession;


}
