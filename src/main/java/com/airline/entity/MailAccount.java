package com.airline.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MailAccount implements Serializable {
    public static final long serialVersionUID = 1L;

    private String mail;
    private String password;
    private String platform;
    private String verifyCode;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
