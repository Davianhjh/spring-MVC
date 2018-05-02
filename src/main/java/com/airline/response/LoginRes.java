package com.airline.response;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class LoginRes extends baseResponse implements Serializable {
    public static final long serialVersionUID = 1L;

    public LoginRes(){}

    private String name;
    private String token;
    private String verifyCode;

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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
