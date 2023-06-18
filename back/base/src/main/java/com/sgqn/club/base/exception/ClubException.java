package com.sgqn.club.base.exception;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 18:49
 */
public class ClubException extends TopClubException {

    public static ClubException CLUB_NOT_FOUND_EXCEPTION = new ClubException(" 社团不存在 ");

    public ClubException(String message) {
        super(message);
    }
}
