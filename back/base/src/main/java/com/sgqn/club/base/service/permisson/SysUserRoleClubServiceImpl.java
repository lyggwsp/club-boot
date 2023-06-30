package com.sgqn.club.base.service.permisson;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.entity.SysUserRoleClub;
import com.sgqn.club.base.mapper.SysUserRoleClubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@Service
public class SysUserRoleClubServiceImpl extends ServiceImpl<SysUserRoleClubMapper, SysUserRoleClub> implements SysUserRoleClubService {

    @Autowired
    private SysUserRoleClubMapper sysUserRoleClubMapper;

    @Override
    public void deleteByClubId(Long id) {
        sysUserRoleClubMapper.deleteByClubId(id);
    }
}
