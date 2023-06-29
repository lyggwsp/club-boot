package com.sgqn.club.base.dto.req.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author wspstart
 * @description
 * @create 2023-06-22 0:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户登录请求实体[AuthLoginReq]")
public class AuthLoginReq {

    @ApiModelProperty(value = "账号", required = true, example = "admin")
    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 4, max = 16, message = "账号长度为 4-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;

    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @ApiModelProperty(value = "社团编号" ,example = "10010")
    private Long clubId;

    @ApiModelProperty(value = "角色编号" ,example = "1671323229551681538")
    private Long roleId;

}
