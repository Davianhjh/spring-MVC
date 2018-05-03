package com.airline.service;

import com.airline.entity.*;

public interface MemberLogin {

    LoginData searchAccount (MailAccount mailAccount);

    LoginData searchAccount (TelAccount telAccount);

    AccountData quickAccess (TelAccount telAccount);

    void recordToken (TokenData tokenData);

    void recordQuickAccess (TelAccount telAccount);

}
