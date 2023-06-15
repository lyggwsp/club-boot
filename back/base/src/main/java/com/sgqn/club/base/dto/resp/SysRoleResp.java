package com.sgqn.club.base.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wspstart
 * @description
 * @create 2023-06-13 22:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("系统角色[SysRoleResp]")
public class SysRoleResp {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述[存储有关角色的详细说明或相关信息]")
    private String description;


    @ApiModelProperty(value = "前端排序")
    private Integer sort;

    @ApiModelProperty(value = "角色代码")
    private String code;


    @ApiModelProperty(value = "角色状态")
    private Integer status;

    @ApiModelProperty(value = "角色类型[0表示系统内置，1表示自定义]")
    private Integer type;

}
