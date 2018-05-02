package com.airline.service;

import com.airline.dao.LoginDao;
import com.airline.entity.LoginData;
import com.airline.entity.MailAccount;
import com.airline.entity.TelAccount;
import com.airline.entity.TokenData;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberLoginImpl implements MemberLogin {

    @Resource
    private LoginDao loginDao;

    public LoginData searchAccount(MailAccount mailAccount) {
        return loginDao.mailLogin(mailAccount);
    }

    public LoginData searchAccount(TelAccount telAccount) {
        return loginDao.telLogin(telAccount);
    }

    public Integer quickAccess(TelAccount telAccount) {
        return loginDao.quickLogin(telAccount);
    }

    public void recordToken (TokenData tokenData) {
        if (loginDao.searchToken(tokenData.getUid()) != null) {
             loginDao.updateToken(tokenData);
        } else {
            loginDao.createToken(tokenData);
        }
    }

    public void recordQuickAccess(TelAccount telAccount) {
        StringBuffer verifyCode = new StringBuffer("");
        for(int i=0; i<6; i++){
            int tmp = (int)Math.floor(Math.random()*10);
            verifyCode.append(tmp);
        }
        telAccount.setVerifyCode(verifyCode.toString());
        telAccount.setPassword(BCrypt.hashpw(telAccount.getPassword(), BCrypt.gensalt()));
        loginDao.insertQuickInfo(telAccount);
    }
}
