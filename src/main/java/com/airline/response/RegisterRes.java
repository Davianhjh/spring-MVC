package com.airline.response;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RegisterRes extends baseResponse implements Serializable {
    public static final long serialVersionUID = 1L;

    private int register;
    private String verifyCode;

    public RegisterRes(){}

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
