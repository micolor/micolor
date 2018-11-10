package com.micolor.common.exception;


import com.micolor.common.constants.ResponseConstants;

/**
 * Created by ace on 2017/9/8.
 */
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, ResponseConstants.EX_USER_INVALID_CODE);
    }
}
