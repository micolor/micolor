package com.micolor.admin.app.controller;

import com.micolor.admin.app.dto.SysUser;
import com.micolor.admin.app.service.UserService;
import com.micolor.common.base.ApiResponse;
import com.micolor.common.constants.CommonConstants;
import com.micolor.common.jwt.Audience;
import com.micolor.common.jwt.JwtHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Ann
 * @Date 2018/11/7 下午10:55
 * @Description
 */
@Api(value="loginController",tags={"用户登陆相关接口"})
@RestController
@RequestMapping
public class LoginController {
    // private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;
    @Autowired
    private Audience audienceEntity;

    @ApiOperation(value = "用户登陆", notes = "根据用户名密码获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientId", value = "密钥", required = true, dataType = "String", paramType = "query", defaultValue = "098f6bcd4621d373cade4e832627b4f6"),
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query", defaultValue = "admin"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456")
    })
    @PostMapping(value = "/login")
    public ApiResponse getAccessToken(HttpServletRequest request) {
        String clientId = request.getParameter("clientId");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if (StringUtils.isEmpty(clientId) || (clientId.compareTo(audienceEntity.getClientId()) != 0)) {
            return new ApiResponse(false, "用户参数错误");
        }
        //验证用户名密码
        SysUser user = userService.validate(userName, password);
        //拼装accessToken
        String accessToken = JwtHelper.createJWT(userName, String.valueOf(user.getId()),
                "", audienceEntity.getClientId(), audienceEntity.getName(),
                audienceEntity.getExpiresSecond() * 1000L, audienceEntity.getBase64Secret());
        String token = CommonConstants.TOKEN_PRE + " " + accessToken;
        return new ApiResponse(token);
    }


    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户信息")
    @GetMapping(value = "/api/userInfo")
    public ApiResponse getUserInfo() {
        SysUser user = userService.getUserById("1");
        return new ApiResponse(user);
    }
}
