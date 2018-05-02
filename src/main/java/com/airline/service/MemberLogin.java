package com.airline.service;

import com.airline.entity.LoginData;
import com.airline.entity.MailAccount;
import com.airline.entity.TelAccount;
import com.airline.entity.TokenData;

public interface MemberLogin {

    LoginData searchAccount (MailAccount mailAccount);

    LoginData searchAccount (TelAccount telAccount);

    Integer quickAccess (TelAccount telAccount);

    void recordToken (TokenData tokenData);

    void recordQuickAccess (TelAccount telAccount);

}
