package com.sgqn.club.base.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgqn.club.base.entity.Department;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 根据社团编号和部门编号获取部门信息
     *
     * @param clubId 社团编号
     * @param deptId 部门编号
     * @return 返回部门信息
     */
    default Department selectByClubIdAndDepartId(Long clubId, Long deptId) {
        return selectOne(new LambdaQueryWrapper<Department>()
                .eq(Department::getClubId, clubId).eq(Department::getId, deptId));
    }
}
