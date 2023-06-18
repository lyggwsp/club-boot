package com.sgqn.club.base.dto.resp.permission;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wspstart
 * @description
 * @create 2023-06-17 21:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRoleExcelResp {

    @ExcelProperty(" 角色编号 ")
    private String id;

    @ExcelProperty("角色名称")
    private String name;

    @ExcelProperty("角色描述")
    private String description;

    @ExcelProperty("角色标志")
    private String code;

    @ExcelProperty("角色排序")
    private Integer sort;

    @ExcelProperty("角色状态")
    private String status;

    @ExcelProperty(value = "角色类型")
    private String type;

    @ExcelProperty(value = "创建时间")
    private String createTime;
}
