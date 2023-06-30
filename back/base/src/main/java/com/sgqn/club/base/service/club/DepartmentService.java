package com.sgqn.club.base.service.club;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.dto.req.club.ClubReq;
import com.sgqn.club.base.entity.Club;
import com.sgqn.club.base.entity.Department;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-18
 */
public interface DepartmentService extends IService<Department> {


    /**
     * 根据社团ID删除部门信息
     *
     * @param id 社团编号
     */
    void deleteByClubId(Long id);

    Long create(ClubReq clubReq);

    void delete(Long id);

    void update(ClubReq clubReq);

    void updateStatus(Long id, Integer status);

    List<Department> getAll();
}
