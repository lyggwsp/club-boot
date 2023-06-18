package com.sgqn.club.base.dto.req.permission.menu;

import com.sgqn.club.base.validation.ValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 9:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("系统更新菜单[SysMenuCreateReq]")
public class SysMenuUpdateReq {

    @ApiModelProperty(value = "菜单编号", required = true, example = "1024")
    @NotNull(groups = ValidGroup.Update.class, message = "菜单编号不能为空")
    private String id;


    @ApiModelProperty("菜单名称：通常用于标识菜单项的唯一性或作为菜单项的识别符。")
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String name;

    @ApiModelProperty(value = "类型,参考MenuTypeEnum类型", example = "1")
    @NotNull(message = "菜单类型不能为空")
    private Integer menuType;

    @ApiModelProperty("菜单页面标题：用于显示菜单项的文本或标题。")
    @NotBlank(message = "菜单标题不能为空")
    @Size(max = 50, message = "菜单页面标题长度不能超过50个字符")
    private String title;

    @ApiModelProperty(value = "菜单图标,仅菜单类型为菜单或者目录时，才需要传", example = "/menu/list")
    private String icon;


    @ApiModelProperty(value = "菜单徽标（Menu Badge）是指在菜单项上显示的小图标或数字，", example = "1或iframe")
    private String badge;

    @ApiModelProperty(value = "页面打开的窗口", example = "_self或者_blank")
    private String target;

    @ApiModelProperty(value = "路由地址,仅菜单类型为菜单或者目录时，才需要传", example = "/workplace")
    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;

    @ApiModelProperty(value = "组件路径,仅菜单类型为菜单时，才需要传", example = "@/pages/workplace/index")
    @Size(max = 200, message = "组件路径不能超过255个字符")
    private String component;

    @ApiModelProperty(value = "是否渲染菜单", example = "true")
    private Boolean renderMenu;

    @ApiModelProperty(value = "是否缓存", example = "false")
    private Boolean cacheable;

    @ApiModelProperty(value = "权限标识，仅菜单类型为按钮时,才需要传递", example = "system:menu:add")
    @Size(max = 100)
    private String permission;

    @ApiModelProperty(value = "页面是否可关闭", example = "是")
    private Boolean closeable;

    @ApiModelProperty(value = "父菜单 ID ", example = "1024")
    private Long parentId;

    @ApiModelProperty(value = "显示排序", example = "1024")
    @NotNull(message = "显示排序不能为空")
    private Integer sort;

    @ApiModelProperty(value = "状态([ 新增 ] 时必填项)", allowableValues = "0,1")
    @Min(value = 0, message = "状态值必须为0或1")
    @Max(value = 1, message = "状态值必须为0或1")
    private Integer status;

}
