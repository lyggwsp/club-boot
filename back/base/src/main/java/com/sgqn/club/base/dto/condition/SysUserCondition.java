package com.sgqn.club.base.dto.condition;

import com.sgqn.club.base.bean.PageCondition;
import com.sgqn.club.base.entity.ClubRoleUserPage;
import com.sgqn.club.base.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 13:24
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户分页查询[SysUserCondition]")
public class SysUserCondition extends PageCondition<ClubRoleUserPage> {

    @ApiModelProperty(value = "社团编号", example = "1024")
    private Long clubId;

    @ApiModelProperty(value = "部门编号", example = "1025")
    private Long deptId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "邮箱")
    @Pattern(regexp = "^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "状态")
    @Min(value = 0, message = "状态值必须为0或1")
    @Max(value = 1, message = "状态值必须为0或1")
    private Integer status;


}
