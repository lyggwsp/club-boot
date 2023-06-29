package com.sgqn.club.base.exception;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 18:45
 */
public class UserException extends TopClubException {


    public static UserException USER_NOT_FOUND_EXCEPTION = new UserException(" 用户不存在 ");
    public static UserException AUTH_LOGIN_BAD_CREDENTIALS = new UserException(" 登录失败，账号密码不正确 ");
    public static UserException AUTH_LOGIN_USER_DISABLED = new UserException(" 登录失败，账号被禁用 ");
    public static UserException USERNAME_EXISTS = new UserException(" 用户名已经存在 ");

    public UserException(String message) {
        super(message);
    }
}
