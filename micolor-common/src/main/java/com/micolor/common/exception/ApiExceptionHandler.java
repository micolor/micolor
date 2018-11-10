package com.micolor.common.exception;

import com.micolor.common.base.ApiResponse;
import com.micolor.common.constants.ResponseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 异常处理
 */
@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);


    @ExceptionHandler(UserTokenException.class)
    public ApiResponse userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(ResponseConstants.SUCCESS_CODE);
        logger.error(ex.getMessage(), ex);
        return new ApiResponse(false, ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(UserInvalidException.class)
    public ApiResponse userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(ResponseConstants.SUCCESS_CODE);
        logger.error(ex.getMessage(), ex);
        return new ApiResponse(false, ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(SQLException.class)
    public ApiResponse sqlExceptionHandler(HttpServletResponse response, SQLException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(ResponseConstants.SUCCESS_CODE);
        return new ApiResponse(false, ResponseConstants.EX_SQL_MSG, ResponseConstants.EX_COMMON_CODE);
    }

    @ExceptionHandler(BaseException.class)
    public ApiResponse baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(ResponseConstants.SUCCESS_CODE);
        return new ApiResponse(false, ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(ResponseConstants.SUCCESS_CODE);
        logger.error(ex.getMessage(), ex);
        return new ApiResponse(false, ResponseConstants.ERROR, ResponseConstants.EX_COMMON_CODE);
    }

}
