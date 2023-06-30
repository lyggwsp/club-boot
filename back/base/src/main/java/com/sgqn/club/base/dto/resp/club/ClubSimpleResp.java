package com.sgqn.club.base.dto.resp.club;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 22:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("社团信息精简响应实体[SysMenuSimpleResp]")
public class ClubSimpleResp {

    @ApiModelProperty(value = "社团编号", example = "1024")
    private Long id;

    @ApiModelProperty(value = "社团名称", example = "测试社团名称")
    private String name;

    @ApiModelProperty(value = "前端排序", example = "1024")
    private Integer sort;

}
