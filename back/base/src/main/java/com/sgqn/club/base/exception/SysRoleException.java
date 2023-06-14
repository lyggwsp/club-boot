package com.sgqn.club.base.exception;

/**
 * @author wspstart
 * @description
 * @create 2023-06-14 17:57
 */
public class SysRoleException extends ClubException {

    public static final SysRoleException ID_NULL = new SysRoleException(" 角色ID不能为空 ");
    public static final SysRoleException RoleNotFoundException = new SysRoleException(" 角色不存在 ");

    public SysRoleException(String message) {
        super(message);
    }
}
