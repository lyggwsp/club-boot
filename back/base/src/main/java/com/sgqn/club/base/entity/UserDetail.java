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
 * @since 2023-06-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_detail")
public class UserDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 出生年月
     */
    @TableField("birth_date")
    private Date birthDate;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 年级
     */
    @TableField("gard")
    private Integer gard;

    /**
     * 班级
     */
    @TableField("class")
    private String class_;

    /**
     * 学院
     */
    @TableField("college")
    private String college;

    /**
     * 专业
     */
    @TableField("major")
    private String major;

    /**
     * 入学时间
     */
    @TableField("intake")
    private Date intake;


}
