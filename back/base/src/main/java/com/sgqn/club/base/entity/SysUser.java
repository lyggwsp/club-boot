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
@TableName("sys_user")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用于登录的用户名
     */
    @TableField("username")
    private String username;

    /**
     * 用户登录的密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 邮箱地址
     */
    @TableField("email")
    private String email;

    /**
     * 是否是管理员
     */
    @TableField("is_admin")
    private Integer isAdmin;

    /**
     * 对应着用户详情表ID
     */
    @TableField("detail_id")
    private Long detailId;


}
