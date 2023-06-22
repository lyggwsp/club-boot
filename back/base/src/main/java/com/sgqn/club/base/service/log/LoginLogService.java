package com.sgqn.club.base.service.log;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.entity.LoginLog;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-22
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 创建一条登录日志信息
     *
     * @param loginLog 日志
     * @return 日志编号
     */
    void createLogin(LoginLog loginLog);
}
