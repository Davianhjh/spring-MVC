package com.airline.service;

import com.airline.dao.DelVerifyDao;
import com.airline.entity.TelAccount;
import com.airline.entity.VerifyRegister;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeleteVerifyImpl implements DeleteVerify {

    @Resource
    private DelVerifyDao delVerifyDao;

    public void deleteVerifyInfo (VerifyRegister verifyRegister) {
        delVerifyDao.delVerifyInfo(verifyRegister);
    }

    public void deleteQuickInfo(TelAccount telAccount) {
        delVerifyDao.delQuickInfo(telAccount);
    }
}
