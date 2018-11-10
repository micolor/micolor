/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: UserService
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * Email Address: liwei@ibeifeng.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.micolor.admin.app.service;

import com.micolor.admin.app.dto.SysUser;
import com.micolor.admin.app.mapper.UserMapper;

import com.micolor.common.constants.ResponseConstants;
import com.micolor.common.exception.UserInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author Ann
 * @create 2018/10/12 0012
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public SysUser validate(String username, String password) throws UserInvalidException {
        SysUser info = new SysUser();
        info.setUserName(username);
        SysUser user = userMapper.selectOne(info);

        if (encoder.matches(password, user.getPassword())) {
            info.setId(user.getId());
            return user;
        }

        throw new UserInvalidException(ResponseConstants.EX_USER_PASS_INVALID_MSG);
    }
    public SysUser getUserById(String userId) {
        SysUser user = null;
        try {
            user = userMapper.selectByPrimaryKey(userId);
        } catch (Exception e) {
            e.getMessage();
        }
        return user;
    }
}
