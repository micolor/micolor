package com.micolor.common.utils.tablelist;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author Ann
 * @Date 2018/11/23 下午10:38
 * @Description
 */
public class Http2TableList {
    public static TableList getHttp2TableList(HttpServletRequest request){
        String dtJson = request.getParameter("dtJson");
        TableList tableList = null;
        if (StringUtils.isNotBlank(dtJson)) {
             tableList = JSON.parseObject(dtJson, TableList.class);
        }
        Map<String, Class> clsMap = new HashMap();
        return tableList;
    }
}
