package com.sgqn.club.base.dto.resp.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 15:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("系统菜单精简实体[SysMenuSimpleResp]")
public class SysMenuSimpleResp {
    @ApiModelProperty(value = "菜单编号", required = true, example = "1024")
    private String id;

    @ApiModelProperty(value = "菜单名称", required = true, example = "芋道")
    private String name;

    @ApiModelProperty(value = "菜单页面标题", required = true, example = "芋道")
    private String title;

    @ApiModelProperty(value = "父菜单 ID", required = true, example = "1024")
    private String parentId;

    @ApiModelProperty(value = "类型，参见 MenuTypeEnum 枚举类", required = true, example = "1")
    private Integer menuType;
}
