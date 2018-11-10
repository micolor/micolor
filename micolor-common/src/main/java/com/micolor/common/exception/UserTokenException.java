package com.micolor.common.exception;


import com.micolor.common.constants.ResponseConstants;

/**
 * Created by ace on 2017/9/8.
 */
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, ResponseConstants.EX_TOKEN_INVALID_CODE);
    }
}
