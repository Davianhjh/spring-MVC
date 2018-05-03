package com.airline.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AccountData extends TelAccount implements Serializable {
    public static final long serialVersionUID = 1L;

    private int uid;
    private String userName;

    public AccountData(){}

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
