package com.micolor.admin.app.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micolor.admin.app.dto.SysApiLog;
import com.micolor.admin.app.mapper.ApiLogMapper;
import com.micolor.common.utils.tablelist.TableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiLogService {

    @Autowired
    private ApiLogMapper apiLogMapper;

    public void saveLog(SysApiLog log) {
        apiLogMapper.insertSelective(log);
    }

    public Map getLogs(TableList tableList){
        PageHelper.startPage(tableList.getStart(), tableList.getLength());
        List<SysApiLog> list = apiLogMapper.selectAll();
        PageInfo<Map> pageInfo = new PageInfo(list);
        Map res = new HashMap();
        res.put("total", pageInfo.getTotal());
        res.put("list", list);
        return res;
    }
}
