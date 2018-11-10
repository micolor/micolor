package com.micolor.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @Author Ann
 * @Date 2018/11/7 16:13
 * @Description 常用方法
 *
 */
public class StringUtil {

    private StringUtil() {

    }

    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 获取登录用户远程主机ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String unknown = "unknow";
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
