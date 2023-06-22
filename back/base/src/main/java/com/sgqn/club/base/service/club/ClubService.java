package com.sgqn.club.base.service.club;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.entity.Club;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-18
 */
public interface ClubService extends IService<Club> {

    /**
     * 从缓存中获取社团信息
     * @param clubId  社团编号
     * @return 社团信息
     */
    Club getRoleFromCache(Long clubId);
}
