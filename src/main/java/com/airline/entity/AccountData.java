package com.airline.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AccountData extends baseAccount implements Serializable {
    public static final long serialVersionUID = 1L;

    private int id;
    private String mail;
    private String telCountry;
    private String tel;
    private String userName;

    public AccountData(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelCountry() {
        return telCountry;
    }

    public void setTelCountry(String telCountry) {
        this.telCountry = telCountry;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
