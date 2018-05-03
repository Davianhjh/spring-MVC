package com.airline.service;

import com.airline.entity.AccountData;
import com.airline.entity.TelAccount;
import com.airline.entity.VerifyRegister;

public interface AccountVerify {

    AccountData verify (VerifyRegister verifyRegister);

    void createAccount (AccountData accountData);

    AccountData verify (TelAccount telAccount);

}
