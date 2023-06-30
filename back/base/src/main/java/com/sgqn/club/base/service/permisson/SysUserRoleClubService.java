package com.sgqn.club.base.service.permisson;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.entity.SysUserRoleClub;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
public interface SysUserRoleClubService extends IService<SysUserRoleClub> {

    /**
     * 根据社团 Id 删除用户-社团-角色信息
     *
     * @param id 社团ID
     */
    void deleteByClubId(Long id);
}
