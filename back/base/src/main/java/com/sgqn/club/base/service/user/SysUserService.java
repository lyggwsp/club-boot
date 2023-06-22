package com.sgqn.club.base.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.entity.SysUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
public interface SysUserService extends IService<SysUser> {


    SysUser getByUserName(String username);


    boolean isPasswordMatch(String rawPassword, String encodedPassword);
}
