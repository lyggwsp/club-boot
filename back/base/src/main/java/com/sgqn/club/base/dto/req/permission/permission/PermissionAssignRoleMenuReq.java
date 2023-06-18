package com.sgqn.club.base.dto.req.permission.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 16:32
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("赋予角色数据权限实体[PermissionAssignRoleDataScopeReq]")
public class PermissionAssignRoleMenuReq {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    @NotNull(message = "角色编号不能为空")
    private Long roleId;

    @ApiModelProperty(value = "菜单编号列表", example = "1,3,5")
    private Set<Long> menuIds = Collections.emptySet(); // 兜底


}
