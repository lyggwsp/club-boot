package com.sgqn.club.base.service.club;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.entity.Club;
import com.sgqn.club.base.mapper.ClubMapper;
import com.sgqn.club.base.service.club.ClubService;
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
    public Club getRoleFromCache(Long clubId) {
        // TODO 待实现
        return clubMapper.selectById(clubId);
    }
}
