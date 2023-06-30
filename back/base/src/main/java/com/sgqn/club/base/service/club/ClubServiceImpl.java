package com.sgqn.club.base.service.club;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.dto.convert.club.ClubConvert;
import com.sgqn.club.base.dto.req.club.ClubReq;
import com.sgqn.club.base.entity.Club;
import com.sgqn.club.base.exception.ClubException;
import com.sgqn.club.base.mapper.ClubMapper;
import com.sgqn.club.base.service.permisson.SysUserRoleClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-18
 */
@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club> implements ClubService {

    @Autowired
    private ClubMapper clubMapper;

    @Autowired
    private SysUserRoleClubService sysUserRoleClubService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Club getClubFromCache(Long clubId) {
        // TODO 待实现
        return clubMapper.selectById(clubId);
    }

    @Override
    public Long create(ClubReq clubReq) {
        // 1、校验社团唯一邮箱唯一性
        validateForSaveOrUpdate(null, clubReq.getEmail());
        // 2、添加社团信息
        Club club = ClubConvert.req2do(clubReq);
        clubMapper.insert(club);
        return club.getId();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // 1、校验社团是否存在
        validateForSaveOrUpdate(id, null);
        // 2、删除社团信息
        clubMapper.deleteById(id);
        // 3、 删除相关联的其他信息 用户社团角色、部门信息
        sysUserRoleClubService.deleteByClubId(id);
        departmentService.deleteByClubId(id);
    }

    @Override
    public void update(ClubReq clubReq) {
        // 1、校验唯一性
        validateForSaveOrUpdate(Long.valueOf(clubReq.getId()), clubReq.getEmail());
        // 2、更新社团信息
        Club club = ClubConvert.req2do(clubReq);
        clubMapper.updateById(club);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        // 1、校验社团是否存在
        validateForSaveOrUpdate(id, null);
        // 2、更新社团状态
        Club club = new Club();
        club.setId(id);
        club.setStatus(status);
        clubMapper.updateById(club);
    }

    @Override
    public List<Club> getAll() {
        return clubMapper.selectList(null);
    }

    private void validateForSaveOrUpdate(Long id, String email) {
        Club club = clubMapper.selectById(id);
        if (club != null) {
            throw ClubException.CLUB_NOT_FOUND_EXCEPTION;
        }
        Club selectByEmail = clubMapper.selectByEmail(email);
        if (selectByEmail != null && !selectByEmail.getId().equals(id)) {
            throw ClubException.CLUB_EMAIL_EXIST;
        }
    }
}
