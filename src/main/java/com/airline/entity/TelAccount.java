package com.airline.entity;

import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
public class TelAccount extends MailAccount implements Serializable {
    public static final long serialVersionUID = 1L;

    private String telCountry;
    private String tel;

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
