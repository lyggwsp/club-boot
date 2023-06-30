package com.sgqn.club.base.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgqn.club.base.entity.Club;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wspstart
 * @since 2023-06-18
 */
@Mapper
public interface ClubMapper extends BaseMapper<Club> {

    /**
     * 根据邮箱获取社团信息
     * @param email 邮箱
     * @return 社团信息
     */
    default Club selectByEmail(String email) {
        return this.selectOne(new LambdaQueryWrapper<Club>().eq(Club::getEmail, email));
    }
}
