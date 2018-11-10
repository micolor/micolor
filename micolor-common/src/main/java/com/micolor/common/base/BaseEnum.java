package com.micolor.common.base;

/**
 * 基础枚举接口
 *
 * @Author Ann
 * @Date 2018/11/7 16:17
 * @Description
 */
public interface BaseEnum<K, V> {
    /**
     * 获取编码
     *
     * @return 编码
     */
    K code();

    /**
     * 获取描述
     *
     * @return 描述
     */
    V desc();
}
