package com.airline.service;

import com.airline.dao.RegisterDao;
import com.airline.entity.MailAccount;
import com.airline.entity.TelAccount;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AccountRegisterImpl implements AccountRegister {

    @Resource
    private RegisterDao registerDao;

    public boolean isUnique(String mail) {
        return registerDao.isMailUnique(mail) == null;
    }

    public boolean isUnique(String telCountry, String tel) {
        return registerDao.isTelUnique(telCountry, tel) == null;
    }

    public void preRegMail(MailAccount mailAccount) {
        UUID uuid = UUID.randomUUID();
        mailAccount.setVerifyCode(uuid.toString().substring(24,32));
        mailAccount.setPassword(BCrypt.hashpw(mailAccount.getPassword(), BCrypt.gensalt()));
        registerDao.preRegMail(mailAccount);
    }

    public void preRegTel(TelAccount telAccount) {
        StringBuffer verifyCode = new StringBuffer("");
        for(int i=0; i<6; i++){
            int tmp = (int)Math.floor(Math.random()*10);
            verifyCode.append(tmp);
        }
        telAccount.setVerifyCode(verifyCode.toString());
        telAccount.setPassword(BCrypt.hashpw(telAccount.getPassword(), BCrypt.gensalt()));
        registerDao.preRegTel(telAccount);
    }
}
