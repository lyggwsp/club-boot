package com.sgqn.club.base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 14:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubRoleUserPage {

    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 用户名称（学号）
     */
    private String username;


    /**
     * 用户昵称
     */
    private String nickName;


    /**
     * 用户昵称
     */
    private String email;

    /**
     * 所属社团名称
     */
    private String clubName;


    /**
     * 所属社团编号
     */
    private Long clubId;


    /**
     * 所属社团部门编号
     */
    private Long deptId;

    /**
     * 所属社团部门名称
     */
    private String deptName;


    /**
     * 用户信息创建时间
     */
    private LocalDateTime createTime;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 角色编号
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 加入社团时间
     */
    private LocalDateTime joinTime;

    /**
     * 离开社团时间
     */
    private LocalDateTime leaveTime;
}
