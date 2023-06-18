package com.sgqn.club.base.exception;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 9:58
 */
public class SysMenuException extends ClubException {

    public static final SysMenuException MENU_PARENT_ROOT = new SysMenuException(" 不能设置自己为根节点 ");
    public static final SysMenuException MENU_PARENT_NOT_EXISTS = new SysMenuException(" 菜单父项不存在 ");
    public static final SysMenuException MENU_PARENT_NOT_DIR_OR_MENU = new SysMenuException(" 菜单父级不是目录或菜单 ");
    public static final SysMenuException MENU_NAME_DUPLICATE = new SysMenuException(" 已存在相同的菜单名 ");
    public static final SysMenuException MENU_NOT_EXISTS = new SysMenuException(" 菜单不存在 ");
    public static final SysMenuException MENU_EXISTS_CHILDREN = new SysMenuException(" 菜单存在子项 ");

    public SysMenuException(String message) {
        super(message);
    }


}
