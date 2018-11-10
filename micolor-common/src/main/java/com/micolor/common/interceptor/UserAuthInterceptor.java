package com.micolor.common.interceptor;


import com.micolor.common.base.UserContextHandler;
import com.micolor.common.config.IgnoreUserToken;
import com.micolor.common.constants.CommonConstants;
import com.micolor.common.constants.ResponseConstants;
import com.micolor.common.exception.UserTokenException;
import com.micolor.common.jwt.Audience;
import com.micolor.common.jwt.JwtHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ace on 2017/9/10.
 */
public class UserAuthInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(UserAuthInterceptor.class);

    @Autowired
    private Audience audience;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        String auth = request.getHeader(CommonConstants.AUTH_HEADER);
        if (StringUtils.isEmpty(auth)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(CommonConstants.AUTH_HEADER)) {
                        auth = cookie.getValue();
                    }
                }
            }
        }
        //token验证
        if ((auth != null) && (auth.length() > 7)) {
            String headStr = auth.substring(0, 6).toLowerCase();
            if (headStr.compareTo("bearer") == 0) {
                auth = auth.substring(7, auth.length());
                try {
                    Claims claims = JwtHelper.parseJWT(auth, audience.getBase64Secret());
                    UserContextHandler.setUserID(claims.get(CommonConstants.JWT_KEY_USER_ID).toString());
                    UserContextHandler.setUsername(claims.get(CommonConstants.JWT_KEY_USER_NAME).toString());
                    return super.preHandle(request, response, handler);
                } catch (ExpiredJwtException ex) {
                    throw new UserTokenException(ResponseConstants.EX_TOKEN_EXPIRE_MSG);
                } catch (Exception ex) {
                    logger.error("内部错误，原因：{}", ex.getMessage());
                    throw new UserTokenException(ResponseConstants.EX_TOKEN_ERROR_MSG);
                }

            } else {
                throw new UserTokenException(ResponseConstants.EX_TOKEN_ERROR_MSG);
            }
        } else {
            throw new UserTokenException(ResponseConstants.EX_TOKEN_NULL_MSG);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
