package com.sgqn.club.base.dto.req.club;

import com.sgqn.club.base.validation.ValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 21:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("创建||更新社团信息请求实体[AuthLoginReq]")
public class ClubReq {

    @ApiModelProperty(value = "社团编号", required = true, example = "admin")
    @NotEmpty(groups = ValidGroup.Update.class, message = "编号不能为空")
    private String id;

    @ApiModelProperty(value = "社团名称", required = true, example = "admin")
    @NotEmpty(groups = ValidGroup.Insert.class, message = "社团名称不能为空")
    private String name;

    @ApiModelProperty(value = "社团描述", required = true, example = "admin")
    private String description;

    @ApiModelProperty(value = "成立时间", required = true, example = "admin")
    private LocalDate establishedDate;

    @ApiModelProperty(value = "社团当前人数", required = true, example = "admin")
    private Integer curNumberPeople;

    @ApiModelProperty(value = "附属学院", required = true, example = "admin")
    private String affiliatedCollege;

    @ApiModelProperty(value = "社团唯一联系邮箱地址", required = true, example = "admin")
    private String email;

    @ApiModelProperty(value = "前端排序", required = true, example = "admin")
    private Integer sort;

    @ApiModelProperty(value = "当前届数", required = true, example = "admin")
    private String curSession;

    @ApiModelProperty(value = "状态([ 新增 ] 时必填项)", allowableValues = "0,1")
    @Min(value = 0, groups = ValidGroup.Insert.class, message = "状态值必须为0或1")
    @Max(value = 1, groups = ValidGroup.Insert.class, message = "状态值必须为0或1")
    private Integer status;

}
