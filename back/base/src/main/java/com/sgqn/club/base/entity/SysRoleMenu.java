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
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 菜单编号
     */
    @TableField("menu_id")
    private Long menuId;


}
