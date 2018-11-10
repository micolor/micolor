package com.micolor.admin.app.dto;

import com.micolor.common.utils.UUIDGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_api_log")
public class SysApiLog {
    @Id
    @KeySql(genId = UUIDGenId.class)
    @Column(name="id")
    private String id;

    private String name;
    private String uri;

    @Column(name = "create_time")
    private Date createTime;//创建时间

    @Column(name = "create_user")
    private String createUser;//创建人

    @Column(name = "create_host")
    private String createHost;//访问主机

    private Integer state;//返回状态吗
    private String result;//返回结果s
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateHost() {
        return createHost;
    }

    public void setCreateHost(String createHost) {
        this.createHost = createHost;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
