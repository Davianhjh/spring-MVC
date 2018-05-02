package com.airline.service;

import com.airline.entity.MailRegister;

public interface AccountRegister {

    boolean isUnique(String mail);

    //boolean isUnique(String telCountry, String tel);

    void preRegMail(MailRegister mailRegister);
}
