package com.sgqn.club.base.dto.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wspstart
 * @description 系统菜单的条件查询
 * @create 2023-06-18 14:22
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ApiModel("系统菜单条件查询实体[SysMenuCondition]")
public class SysMenuCondition {

    @ApiModelProperty(value = "菜单名称，模糊匹配", example = "工作台")
    private String name;

    @ApiModelProperty(value = "菜单状态,参考 CommonStatusEnum 枚举类")
    private Integer status;
}
