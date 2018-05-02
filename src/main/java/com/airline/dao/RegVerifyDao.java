package com.airline.dao;

import com.airline.entity.AccountData;
import com.airline.entity.VerifyRegister;

public interface RegVerifyDao {

    AccountData verifyAccount (VerifyRegister verifyRegister);

    void createAccount (AccountData accountData);

}
