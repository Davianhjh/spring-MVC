package com.airline.dao;

import com.airline.entity.*;

public interface LoginDao {

    LoginData mailLogin (MailAccount mailAccount);

    LoginData telLogin (TelAccount telAccount);

    AccountData quickLogin (TelAccount telAccount);

    Integer searchToken (int uid);

    void createToken (TokenData tokenData);

    void updateToken (TokenData tokenData);

    void insertQuickInfo (TelAccount telAccount);

}
