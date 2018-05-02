package com.airline.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MailRegister extends baseAccount implements Serializable {
    public static final long serialVersionUID = 1L;

    private String mail;
    private String verifyCode;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
