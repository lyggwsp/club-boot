package com.sgqn.club.base.service.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.entity.UserDetail;
import com.sgqn.club.base.mapper.UserDetailMapper;
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
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail>
        implements UserDetailService {

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Override
    public UserDetail getUserById(Long id) {
        return userDetailMapper.selectById(id);
    }
}
