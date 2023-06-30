package com.sgqn.club.base.service.club;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.dto.req.club.ClubReq;
import com.sgqn.club.base.entity.Department;
import com.sgqn.club.base.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-18
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public void deleteByClubId(Long id) {
        departmentMapper.deleteByClubId(id);
    }

    @Override
    public Long create(ClubReq clubReq) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(ClubReq clubReq) {

    }

    @Override
    public void updateStatus(Long id, Integer status) {

    }

    @Override
    public List<Department> getAll() {
        return null;
    }
}
