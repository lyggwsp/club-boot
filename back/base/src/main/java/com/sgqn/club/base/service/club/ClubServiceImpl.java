package com.sgqn.club.base.service.club;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.dto.convert.club.ClubConvert;
import com.sgqn.club.base.dto.req.club.ClubReq;
import com.sgqn.club.base.entity.Club;
import com.sgqn.club.base.exception.ClubException;
import com.sgqn.club.base.mapper.ClubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void delete(Long id) {

    }

    @Override
    public void update(ClubReq clubReq) {

    }

    @Override
    public void updateStatus(Long id, Integer status) {

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
