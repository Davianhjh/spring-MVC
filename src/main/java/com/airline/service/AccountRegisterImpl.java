package com.airline.service;

import com.airline.dao.RegisterDao;
import com.airline.entity.MailRegister;
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

    public void preRegMail(MailRegister mailRegister) {
        UUID uuid = UUID.randomUUID();
        mailRegister.setVerifyCode(uuid.toString().substring(24,32));
        mailRegister.setPassword(BCrypt.hashpw(mailRegister.getPassword(), BCrypt.gensalt()));
        registerDao.preRegMail(mailRegister);
    }
}
