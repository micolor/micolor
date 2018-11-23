package com.micolor.admin.app.mapper;
import com.micolor.admin.app.dto.SysApiLog;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.jdbc.core.SqlProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface ApiLogMapper extends Mapper<SysApiLog> {
    @SelectProvider(type = SqlProvider.class, method = "select4TableList")
    List<Map> getList(Map parameterMap);
}
