package com.airline.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MailAccount extends baseAccount implements Serializable {
    public static final long serialVersionUID = 1L;

    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
