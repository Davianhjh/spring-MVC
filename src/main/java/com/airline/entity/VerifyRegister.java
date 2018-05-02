package com.airline.entity;

import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
public class VerifyRegister implements Serializable {
    public static final long serialVersionUID = 1L;

    private String verifyCode;
    private String platform;
    private String nowUTC;

    public VerifyRegister(){}

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getNowUTC() {
        return nowUTC;
    }

    public void setNowUTC(String nowUTC) {
        this.nowUTC = nowUTC;
    }
}
