package com.sgqn.club.base.exception;

/**
 * @author wspstart
 * @description
 * @create 2023-06-14 17:57
 */
public class SysRoleException extends TopClubException {

    public static final SysRoleException ID_NULL = new SysRoleException(" 角色ID不能为空 ");
    public static final SysRoleException ROLE_NOT_FOUND_EXCEPTION = new SysRoleException(" 角色不存在 ");
    public static final SysRoleException ROLE_ADMIN_CODE_ERROR = new SysRoleException(" 角色编码不能使用 ");

    public static final SysRoleException ROLE_NAME_DUPLICATE = new SysRoleException(" 角色名已被占用 ");

    public static final SysRoleException ROLE_CODE_DUPLICATE = new SysRoleException(" 角色编码已被占用 ");
    public static final SysRoleException ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE = new SysRoleException(" 无法操作系统内置角色信息 ");

    public SysRoleException(String message) {
        super(message);
    }
}
