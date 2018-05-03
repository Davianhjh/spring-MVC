package com.airline.dao;

import com.airline.entity.TelAccount;
import com.airline.entity.VerifyRegister;

public interface DelVerifyDao {

    void delVerifyInfo (VerifyRegister verifyRegister);

    void delQuickInfo (TelAccount telAccount);

}
