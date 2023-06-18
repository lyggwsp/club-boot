package com.sgqn.club.base.dto.req.permission.permission;

import com.sgqn.club.base.validation.ValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 17:30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("赋予用户-社团-角色实体[PermissionAssignRoleDataScopeReq]")
public class PermissionAssignUserClubRoleReq {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    @NotNull(message = "用户编号不能为空", groups = {ValidGroup.Insert.class})
    private Long userId;

    @ApiModelProperty(value = "角色编号", required = true, example = "2")
    @NotNull(message = "角色编号不能为空", groups = {ValidGroup.Insert.class})
    private Long roleId;

    @ApiModelProperty(value = "社团编号", required = true, example = "3")
    @NotNull(message = "社团编号不能为空", groups = {ValidGroup.Insert.class})
    private Long clubId;

    @ApiModelProperty(value = "加入时间", required = true, example = "2023-12-13 12:12:13")
    @NotNull(message = "加入时间不能为空", groups = {ValidGroup.Insert.class})
    private LocalDateTime joinTime;

    @ApiModelProperty(value = "加入部门", example = "12035")
    private Long deptId;

    @ApiModelProperty(value = "加入第几届的社团", example = "2021")
    private Long numSession;
}
