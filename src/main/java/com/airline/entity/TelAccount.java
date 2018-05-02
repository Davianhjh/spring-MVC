package com.airline.entity;

import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
public class TelAccount extends baseAccount implements Serializable {
    public static final long serialVersionUID = 1L;

    private int uid;
    private String telCountry;
    private String tel;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
}
