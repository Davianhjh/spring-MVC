package com.airline.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class LoginData implements Serializable {
    public static final long serialVersionUID = 1L;

    private int uid;
    private String password;
    private String userName;
    private int credit;

    public LoginData(){}

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}