package com.sgqn.club.base.dto.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wspstart
 * @description
 * @create 2023-06-29 23:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户实体[SysUserReq]")
public class SysUserReq {


    @ApiModelProperty(value = "账号", required = true, example = "admin")
    private String username;

    @ApiModelProperty(value = "密码", required = true, example = "123456")
    private String password;

    @ApiModelProperty(value = "用户所在社团编号", example = "10010")
    private Long clubId;

    @ApiModelProperty(value = "用户的角色编号", example = "10010")
    private Long roleId;

    @ApiModelProperty(value = "昵称", example = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "邮箱", example = "lyggwsp@163.com")
    private String email;

    @ApiModelProperty(value = "状态", example = "lyggwsp@163.com")
    private Integer status;


}
