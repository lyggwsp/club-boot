package com.sgqn.club.base.dto.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 9:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户详情请求实体[SysUserReq]")
public class SysUserDetailReq {

    @ApiModelProperty(value = "用户编号", example = "101100")
    private Long id;

    @ApiModelProperty(value = "邮箱", example = "lyggwsp@163.com")
    @Pattern(regexp = "^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "昵称", example = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "真实姓名", example = "admin")
    private String realName;


    @ApiModelProperty(value = "性别", example = "男")
    private String sex;


    @ApiModelProperty(value = "出生年月日", example = "2022-02-12")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ApiModelProperty(value = "手机号", example = "13052179213")
    private String phone;


    @ApiModelProperty(value = "年级", example = "2023")
    private Integer gard;


    @ApiModelProperty(value = "班级", example = "21软件3")
    private String class_;


    @ApiModelProperty(value = "学院", example = "人工智能与软件学院")
    private String college;


    @ApiModelProperty(value = "专业", example = "软件工程")
    private String major;

    @ApiModelProperty(value = "入学时间", example = "2021-01-09")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate intake;
}
