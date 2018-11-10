package com.micolor.common.utils;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/**
 * @Author Ann
 * @Date 2018/11/7 15:29
 * @Description uuid生成器
 */
public class UUIDGenId implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
