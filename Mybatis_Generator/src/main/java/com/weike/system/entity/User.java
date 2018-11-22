package com.weike.system.entity;

import java.util.Date;

public class User {
    private String id;

    private String loginName;

    private String password;

    private String vsername;

    private String mobile;

    private String email;

    private Date genTime;

    private Date loginTime;

    private Date lastLoginTime;

    private Long count;

    public User(String id, String loginName, String password, String vsername, String mobile, String email, Date genTime, Date loginTime, Date lastLoginTime, Long count) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.vsername = vsername;
        this.mobile = mobile;
        this.email = email;
        this.genTime = genTime;
        this.loginTime = loginTime;
        this.lastLoginTime = lastLoginTime;
        this.count = count;
    }

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getVsername() {
        return vsername;
    }

    public void setVsername(String vsername) {
        this.vsername = vsername == null ? null : vsername.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}