package com.sgqn.club.base.service.club;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.dto.req.club.ClubReq;
import com.sgqn.club.base.entity.Club;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-18
 */
public interface ClubService extends IService<Club> {

    /**
     * 从缓存中获取社团信息
     *
     * @param clubId 社团编号
     * @return 社团信息
     */
    Club getClubFromCache(Long clubId);


    /**
     * 创建社团信息
     *
     * @param clubReq 社团请求
     * @return 返回新增后的社团编号
     */
    Long create(ClubReq clubReq);


    /**
     * 删除社团信息
     *
     * @param id 社团编号
     */
    void delete(Long id);


    /**
     * 更新社团信息
     *
     * @param clubReq 社团请求实体
     */
    void update(ClubReq clubReq);

    /**
     * 更新社团状态信息
     *
     * @param id     社团编号
     * @param status 社团状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 获取所有社团信息
     *
     * @return 社团集合
     */
    List<Club> getAll();
}
