package com.sgqn.club.base.dto.condition;

import com.sgqn.club.base.bean.PageCondition;
import com.sgqn.club.base.entity.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * @author wspstart
 * @description 角色的查询条件
 * @create 2023-06-13 22:37
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("角色条件查询[SysRoleCondition]")
public class SysRoleCondition extends PageCondition<SysRole> {

    @ApiModelProperty("角色名称")
    private String name;


    @ApiModelProperty("排序")
    @Min(value = -1, message = " sort不能小于-1 ")
    @Max(value = 999, message = " sort不能大于999 ")
    private Integer sort;

    @ApiModelProperty("code")
    @Pattern(regexp = "^[a-zA-Z]+$", message = " 角色代码必须是中文 ")
    @NotBlank(message = " 角色代码不能为空 ")
    private String code;

    @ApiModelProperty(value = "状态", allowableValues = "0,1")
    @Min(value = 0, message = "状态值必须为0或1")
    @Max(value = 1, message = "状态值必须为0或1")
    private Integer status;

    @ApiModelProperty(value = "开始创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
