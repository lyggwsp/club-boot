package com.sgqn.club.base.dto.resp.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 13:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户信息分页实体[SysUserPageResp]")
public class ClubRoleUserPageResp {

    @ApiModelProperty(value = "用户编号")
    private String userId;


    @ApiModelProperty(value = "学号")
    private String username;


    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "所属社团")
    private String clubName;

    @ApiModelProperty(value = "社团编号")
    private String clubId;

    @ApiModelProperty(value = "部门编号")
    private String deptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "角色编号")
    private Long roleId;

    @ApiModelProperty(value = "加入社团时间")
    private LocalDateTime joinTime;

    @ApiModelProperty(value = "离开社团时间")
    private LocalDateTime leaveTime;

}
