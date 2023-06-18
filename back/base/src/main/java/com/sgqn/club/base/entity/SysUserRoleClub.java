package com.sgqn.club.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgqn.club.base.bean.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;
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
@TableName("sys_user_role_club")
public class SysUserRoleClub extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 社团编号
     */
    @TableField("club_id")
    private Long clubId;

    /**
     * 角色编号
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 加入时间
     */
    @TableField("join_time")
    private LocalDateTime joinTime;

    /**
     * 离开社团时间
     */
    @TableField("leave_time")
    private LocalDateTime leaveTime;

    /**
     * 加入社团的部门编号
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 加入到社团第几届
     */
    @TableField("num_session")
    private Long numSession;


}
