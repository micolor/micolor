package com.micolor.common.base;


import com.micolor.common.constants.ResponseConstants;

/**
 * @Author Ann
 * @Date 2018/11/7 下午11:22
 * @Description
 */
public class ApiResponse extends BaseResponse {
    private Object data; // 内容数据
    private boolean success;  //状态

    public ApiResponse() {//默认返回成功
        super(ResponseConstants.SUCCESS_CODE, ResponseConstants.OK);
        this.success = true;
    }

    /**
     * 成功返回数据
     *
     * @param data
     */
    public ApiResponse(Object data) {
        super(ResponseConstants.SUCCESS_CODE, ResponseConstants.OK);
        this.success = true;
        this.data = data;
    }

    public ApiResponse(boolean success, String message) {
        super(ResponseConstants.SUCCESS_CODE, message);
        this.success = success;
    }

    public ApiResponse(boolean success, String message, int status) {
        super(status, message);
        this.success = success;
    }

    public ApiResponse(boolean success, String message, int status, Object data) {
        super(status, message);
        this.success = success;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
