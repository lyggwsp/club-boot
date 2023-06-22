package com.sgqn.club.base.service.log;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.entity.LoginLog;
import com.sgqn.club.base.mapper.LoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-22
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void createLogin(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }
}
