package com.sgqn.club.base.dto.req;


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
 * @create 2023-06-14 8:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("系统角色[SysRoleReq]")
public class SysRoleReq {

    @ApiModelProperty("ID([ 更新 /  删除] 时必填项)")
    @NotNull(groups = ValidGroup.Update.class, message = "ID 不能为空")
    @Min(value = 0, groups = ValidGroup.Update.class, message = "ID 小于 0")
    private Long id;


    @ApiModelProperty("角色名称([ 新增 ] 时必填项)")
    @NotBlank(groups = ValidGroup.Insert.class, message = "角色名称 不能为空")
    private String name;

    @ApiModelProperty("角色备注([ 新增 ] 时必填项)")
    @NotBlank(groups = ValidGroup.Insert.class, message = " 描述不能为空 ")
    private String description;

    @ApiModelProperty("排序([ 新增 ] 时必填项)")
    @NotNull(groups = ValidGroup.Insert.class, message = " sort不能为空 ")
    @Min(value = -1, groups = {ValidGroup.Insert.class, ValidGroup.Update.class}, message = " sort不能小于-1 ")
    private Integer sort;

    @ApiModelProperty("code([ 新增  ] 时必填项)")
    @Pattern(regexp = "^[a-zA-Z]+$", message = " 角色代码必须是中文 ")
    private String code;

    @ApiModelProperty("状态([ 新增  ] 时必填项)")
    @NotNull(groups = ValidGroup.Insert.class, message = " 状态值不能为空 ")
    @Min(value = 0, message = "状态值必须是0或1")
    @Max(value = 1, message = "状态值必须是0或1")
    private Integer status;


}
