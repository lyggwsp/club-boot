package com.sgqn.club.controller.club;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.constant.CommonStatusEnum;
import com.sgqn.club.base.dto.convert.club.ClubConvert;
import com.sgqn.club.base.dto.req.club.ClubReq;
import com.sgqn.club.base.entity.Club;
import com.sgqn.club.base.service.club.ClubService;
import com.sgqn.club.base.validation.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Api(tags = "管理后台 - 社团[club-controller]")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @PostMapping("/create")
    @ApiOperation(value = "新增社团[管理端调用]")
    public ResultBean<?> create(@Validated({ValidGroup.Update.class}) ClubReq clubReq) {
        clubService.create(clubReq);
        return ResultBean.success("新增社团信息成功");
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改社团[管理端调用]")
    public ResultBean<?> update(@Validated({ValidGroup.Update.class}) ClubReq clubReq) {
        clubService.update(clubReq);
        return ResultBean.success("修改社团信息成功");
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除社团[管理端调用]")
    public ResultBean<?> delete(Long id) {
        clubService.delete(id);
        return ResultBean.success("删除社团信息成功");
    }

    @PatchMapping("/update-status")
    @ApiOperation(value = "修改社团状态[管理端调用]")
    public ResultBean<?> updateClubStatus(@RequestParam @ApiParam(name = "id", required = true) Long id,
                                          @RequestParam @ApiParam(name = "disabled", required = true) Boolean disabled) {
        clubService.updateStatus(id, disabled ? CommonStatusEnum.DISABLE.getType() : CommonStatusEnum.ENABLE.getType());
        return ResultBean.success("社团状态成功");
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获取用户精简信息列表", consumes = "只包含被开启的用户，主要用于前端的下拉选项")
    public ResultBean<?> getSimpleClub() {
        List<Club> clubList = clubService.getAll();
        return ResultBean.success("获取信息成功", ClubConvert.req2do(clubList));
    }

}
