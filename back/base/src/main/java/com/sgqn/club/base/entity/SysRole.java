package com.sgqn.club.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgqn.club.base.bean.BaseEntity;
import lombok.*;

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
@TableName("sys_role")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 角色描述（存储有关角色的详细说明或相关信息）
     */
    @TableField("description")
    private String description;

    /**
     * 前端排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 角色代码
     */
    @TableField("code")
    private String code;

    /**
     * 角色类型(0表示系统内置，1表示自定义)
     * <p>
     * 枚举 {@link com.sgqn.club.base.constant.SysRoleTypeEnum}
     */
    @TableField("type")
    private Integer type;

    /**
     * 角色状态(0表示禁用，1表示启用)
     * <p>
     * 枚举 {@link com.sgqn.club.base.constant.CommonStatusEnum}
     */
    @TableField("status")
    private Integer status;


}
