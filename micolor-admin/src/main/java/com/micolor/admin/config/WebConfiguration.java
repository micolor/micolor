package com.micolor.admin.config;


import com.micolor.common.exception.ApiExceptionHandler;
import com.micolor.common.interceptor.UserAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * admin项目配置
 */
@Configuration("admimWebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    ApiExceptionHandler getApiExceptionHandler() {
        return new ApiExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserAuthRestInterceptor())
                .addPathPatterns(getIncludePathPatterns())
                .excludePathPatterns("/auth/*")
        ;
    }

    @Bean
    UserAuthInterceptor getUserAuthRestInterceptor() {
        return new UserAuthInterceptor();
    }

    /**
     * 需要用户认证判断的路径
     *
     * @return
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/api/**"
        };
        Collections.addAll(list, urls);
        return list;
    }

}
