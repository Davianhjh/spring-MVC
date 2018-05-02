package com.airline.dao;

import com.airline.entity.LoginData;
import com.airline.entity.MailAccount;
import com.airline.entity.TelAccount;
import com.airline.entity.TokenData;

public interface LoginDao {

    LoginData mailLogin (MailAccount mailAccount);

    LoginData telLogin (TelAccount telAccount);

    Integer quickLogin (TelAccount telAccount);

    Integer searchToken (int uid);

    void createToken (TokenData tokenData);

    void updateToken (TokenData tokenData);

    void insertQuickInfo (TelAccount telAccount);

}
