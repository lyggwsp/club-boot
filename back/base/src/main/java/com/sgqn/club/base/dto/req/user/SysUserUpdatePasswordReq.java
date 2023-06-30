package com.sgqn.club.base.dto.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 11:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户更新密码实体[SysUserUpdatePasswordReq]")
public class SysUserUpdatePasswordReq {

    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    @NotNull(message = "用户编号不能为空")
    private Long id;

    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度为 6-16 位")
    private String password;

}
