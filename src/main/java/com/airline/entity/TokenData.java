package com.airline.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class TokenData implements Serializable {
    public static final long serialVersionUID = 1L;

    private int uid;
    private String token;
    private String create;
    private String platform;

    public TokenData(){}

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
