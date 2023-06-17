package com.sgqn.club.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgqn.club.base.dto.condition.SysRoleCondition;
import com.sgqn.club.base.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 分页条件查询
     * @param page
     * @param condition
     * @return
     */
    IPage<SysRole> selectByCondition(Page<SysRole> page, @Param("condition") SysRoleCondition condition);
}
