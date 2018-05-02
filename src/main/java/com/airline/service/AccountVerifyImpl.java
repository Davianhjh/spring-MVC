package com.airline.service;

import com.airline.dao.RegVerifyDao;
import com.airline.entity.AccountData;
import com.airline.entity.VerifyRegister;
import com.airline.tools.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountVerifyImpl implements AccountVerify {

    @Resource
    private RegVerifyDao regVerifyDao;

    public AccountData verify (VerifyRegister verifyRegister) {
        return regVerifyDao.verifyAccount(verifyRegister);
    }

    public void create (AccountData accountData) {
        String userName = null;
        if (accountData.getMail() != null)
            userName = MD5Util.getMD5(accountData.getMail());
        else userName = MD5Util.getMD5(accountData.getTel());
        accountData.setUserName(userName == null ? null : userName.substring(0,10));
        regVerifyDao.createAccount(accountData);
    }
}
