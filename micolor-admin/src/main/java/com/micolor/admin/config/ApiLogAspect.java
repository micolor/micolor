package com.micolor.admin.config;


import com.micolor.admin.app.dto.SysApiLog;
import com.micolor.admin.app.service.ApiLogService;
import com.micolor.common.base.UserContextHandler;
import com.micolor.common.constants.ResponseConstants;
import com.micolor.common.exception.BaseException;
import com.micolor.common.utils.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.micolor.commo.logs.aspect
 *
 * @Author: Evangoe
 * @Description: 用于对Controller中所有的进行日志记录的切面
 * 每个项目中配置，后期写入网关中
 * @Date:11/05/17
 * @Modified:
 */
@Aspect
@Component
public class ApiLogAspect {
    private static Logger logger = LoggerFactory.getLogger(ApiLogAspect.class);

    private ThreadLocal<SysApiLog> tlocal = new ThreadLocal<SysApiLog>();

    @Autowired
    private ApiLogService apiLogService;

    /*
     * 切面定义
     */
    @Pointcut("(execution(* com.micolor.admin.app.controller.*.*(..))) ")
    public void apiRequestLog() {
    }


    @Before("apiRequestLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String name = joinPoint.getSignature().getName();
            String uri = request.getRequestURI();
            String remoteAddr = StringUtil.getIpAddr(request);
            String userId = UserContextHandler.getUserID();

            SysApiLog optLog = new SysApiLog();
            optLog.setName(name);
            optLog.setUri(uri);
            optLog.setCreateHost(remoteAddr);
            optLog.setCreateUser(userId);
            optLog.setCreateTime(new Date());
            tlocal.set(optLog);
        } catch (Exception e) {
            logger.error("***操作请求日志记录失败doBefore()***", e);
        }
    }

    @SuppressWarnings("unchecked")
    @AfterReturning(value = "apiRequestLog()", argNames = "result", returning = "result")
    public void doAfterReturning(Object result) {
        try {
            // 处理完请求，返回内容
            SysApiLog optLog = tlocal.get();
            optLog.setState(ResponseConstants.SUCCESS_CODE);
            optLog.setResult(ResponseConstants.OK);
            apiLogService.saveLog(optLog);
            tlocal.remove();
        } catch (Exception e) {
            logger.error("***操作请求日志记录失败doAfterReturning()***", e);
        }
    }

    @AfterThrowing(value = "apiRequestLog()", throwing = "ex")
    public void afterThrowing(JoinPoint jp, Exception ex) {
        String strLog = "afterThrowing:log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName();
        logger.error(strLog + "[" + ex + "]");
        SysApiLog optLog = tlocal.get();
        if (ex instanceof BaseException) {
            optLog.setState(((BaseException) ex).getStatus());
        } else {
            optLog.setState(ResponseConstants.EX_COMMON_CODE);
        }
        optLog.setResult(ex.getMessage());
        apiLogService.saveLog(optLog);
        tlocal.remove();
    }
}
