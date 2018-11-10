package com.micolor.admin.app.service;

import com.micolor.admin.app.dto.SysApiLog;
import com.micolor.admin.app.mapper.ApiLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiLogService {

    @Autowired
    private ApiLogMapper apiLogMapper;

    public void saveLog(SysApiLog log) {
        apiLogMapper.insertSelective(log);
    }
}
