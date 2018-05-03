package com.airline.service;

import com.airline.entity.TelAccount;
import com.airline.entity.VerifyRegister;

public interface DeleteVerify {

    void deleteVerifyInfo (VerifyRegister verifyRegister);

    void deleteQuickInfo (TelAccount telAccount);

}
