package com.micolor.common.utils.tablelist.sqlprvider;

import com.micolor.common.utils.tablelist.TableList;

import java.util.Map;

/**
 * @Author Ann
 * @Date 2018/11/24 上午12:04
 * @Description
 */
public class SqlProvider {

    public SqlProvider() {
    }

    public String select4TableList(Map<String, Object> param) {
        TableList dataTables = (TableList) param.get("tableList");
        String baseSql = String.valueOf(param.get("sql"));
        String sql = "a";//DataTables4Hql.DataTables2Hql(baseSql, dataTables);
        return sql;
    }


}
