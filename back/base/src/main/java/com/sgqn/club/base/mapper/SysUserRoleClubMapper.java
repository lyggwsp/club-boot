package com.sgqn.club.base.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgqn.club.base.entity.SysUserRoleClub;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@Mapper
public interface SysUserRoleClubMapper extends BaseMapper<SysUserRoleClub> {

    /**
     * 根据用户ID获取用户具有的角色信息
     *
     * @param userId 用户编号
     * @return 返回用户具有的角色信息 --> SysUserRoleCLub
     */
    default List<SysUserRoleClub> selectListByUserId(Long userId) {
        return this.selectList(new LambdaQueryWrapper<SysUserRoleClub>()
                .eq(SysUserRoleClub::getUserId, userId));
    }
}
