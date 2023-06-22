package com.sgqn.club.base.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.entity.UserDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
public interface UserDetailService extends IService<UserDetail> {


    /**
     * 根据用户ID获取用户信息
     * @param id 用户ID
     * @return 用户详细
     */
    UserDetail getUserById(Long id);

}
