package com.airline.service;

import com.airline.entity.MailAccount;
import com.airline.entity.TelAccount;

public interface AccountRegister {

    boolean isUnique(String mail);

    boolean isUnique(String telCountry, String tel);

    void preRegMail(MailAccount mailAccount);

    void preRegTel(TelAccount telAccount);
}
