package com.airline.response;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RegVerifyRes extends baseResponse implements Serializable {
    public static final long serialVersionUID = 1L;

    private int verify;
    private String name;
    private String token;

    public RegVerifyRes(){}

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
