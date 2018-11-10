package com.micolor.common.base;

import com.micolor.common.constants.CommonConstants;
import com.micolor.common.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Ann
 * @Date 2018/11/9 上午12:03
 * @Description
 */
public class UserContextHandler {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getUserID() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_ID);
        return StringUtil.getObjectValue(value);
    }

    public static String getUserName() {
        Object value = get(CommonConstants.CONTEXT_KEY_USERNAME);
        return StringUtil.getObjectValue(value);
    }


    public static String getToken() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return StringUtil.getObjectValue(value);
    }

    public static void setToken(String token) {
        set(CommonConstants.CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setUserID(String userID) {
        set(CommonConstants.CONTEXT_KEY_USER_ID, userID);
    }

    public static void setUsername(String username) {
        set(CommonConstants.CONTEXT_KEY_USERNAME, username);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
