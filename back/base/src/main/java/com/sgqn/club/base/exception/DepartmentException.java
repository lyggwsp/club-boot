package com.sgqn.club.base.exception;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 21:29
 */
public class DepartmentException extends TopClubException {

    public static DepartmentException DEPARTMENT_NOT_FOUND_EXCEPTION = new DepartmentException(" 部门不存在 ");

    public DepartmentException(String message) {
        super(message);
    }
}
