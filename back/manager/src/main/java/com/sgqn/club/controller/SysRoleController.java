package com.sgqn.club.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.dto.condition.SysRoleCondition;
import com.sgqn.club.base.dto.convert.SysRoleConvert;
import com.sgqn.club.base.dto.resp.SysRoleResp;
import com.sgqn.club.base.entity.SysRole;
import com.sgqn.club.base.service.SysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    @PostMapping("/condition")
    @ApiOperation("分页条件查询[getByCondition]")
    public ResultBean<IPage<SysRoleResp>> getByCondition(SysRoleCondition condition) {
        Page<SysRole> page = sysRoleService.page(condition.getPage());
        if (page.getSize() != 0) {
            IPage<SysRoleResp> respPage = SysRoleConvert.do2resp(page);
            return ResultBean.success("查询成功", respPage);
        }
        return ResultBean.notFound("无角色信息");
    }


}
