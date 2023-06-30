package com.sgqn.club.controller.club;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.dto.req.club.ClubReq;
import com.sgqn.club.base.service.club.ClubService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wspstart
 * @since 2023-06-18
 */
@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @PostMapping("/create")
    @ApiOperation(value = "新增社团[管理端调用]")
    public ResultBean<?> create(ClubReq clubReq) {
        clubService.create(clubReq);
        return ResultBean.success("新增社团信息成功");
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改社团[管理端调用]")
    public ResultBean<?> update() {
        return ResultBean.error("功能待实现");
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除社团[管理端调用]")
    public ResultBean<?> delete(Long id) {
        return ResultBean.error("功能待实现");
    }

    @PatchMapping("/update-status")
    @ApiOperation(value = "修改社团状态[管理端调用]")
    public ResultBean<?> updateClubStatus(@RequestParam @ApiParam(name = "id", required = true) Long id,
                                          @RequestParam @ApiParam(name = "disabled", required = true) Boolean disabled) {
        return ResultBean.error("功能待实现");
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获取用户精简信息列表", consumes = "只包含被开启的用户，主要用于前端的下拉选项")
    public ResultBean<?> getSimpleClub() {
        return ResultBean.error("功能未实现");
    }

}
