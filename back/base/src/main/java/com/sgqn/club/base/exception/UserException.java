package com.sgqn.club.base.exception;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 18:45
 */
public class UserException extends TopClubException {


    public static UserException USER_NOT_FOUND_EXCEPTION = new UserException(" 用户不存在 ");

    public UserException(String message) {
        super(message);
    }
}
