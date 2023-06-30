package com.sgqn.club.base.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgqn.club.base.dto.condition.SysUserCondition;
import com.sgqn.club.base.entity.ClubRoleUserPage;
import com.sgqn.club.base.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface SysUserMapper extends BaseMapper<SysUser> {

    default SysUser selectByUsername(String username) {
        return this.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }


    default SysUser selectByEmail(String email) {
        return this.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getEmail, email));
    }

    IPage<ClubRoleUserPage> selectClubRoleUserByPage(IPage<ClubRoleUserPage> page, @Param("condition") SysUserCondition sysUserCondition);

}
