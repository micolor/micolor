package com.micolor.admin.app.controller;

import com.micolor.admin.app.service.ApiLogService;
import com.micolor.common.base.ApiResponse;
import com.micolor.common.utils.tablelist.Http2TableList;
import com.micolor.common.utils.tablelist.TableList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author Ann
 * @Date 2018/11/21 下午9:16
 * @Description
 */
@Api(value="apiLogMapperContrller",tags={"系统日志"})
@RestController
@RequestMapping
public class ApiLogMapperContrller {
    @Autowired
    ApiLogService apiLogService;
    @ApiOperation(value = "获取系统日志", notes = "获取系统日志")
    @GetMapping(value = "/logs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dtJson", value = "列表参数", dataType = "String", paramType = "query"),
    })
    public ApiResponse getLogs(HttpServletRequest request) {
        TableList tableList = Http2TableList.getHttp2TableList(request);
        Map info = apiLogService.getLogs(tableList);
        return new ApiResponse(info);
    }
}
