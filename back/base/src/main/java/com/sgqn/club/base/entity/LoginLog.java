package com.sgqn.club.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgqn.club.base.bean.BaseEntity;
import java.io.Serializable;

import io.swagger.models.auth.In;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wspstart
 * @since 2023-06-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_login_log")
public class LoginLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 登录类型
     */
    @TableField("log_type")
    private Integer logType;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 社团ID
     */
    @TableField("club_id")
    private Long clubId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 登录结果
     */
    @TableField("result")
    private Integer result;

    /**
     * 登录IP
     */
    @TableField("user_ip")
    private String userIp;

    /**
     * 登录使用浏览器
     */
    @TableField("user_agent")
    private String userAgent;


}
