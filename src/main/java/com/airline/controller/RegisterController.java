package com.airline.controller;

import com.airline.entity.MailRegister;
import com.airline.response.RegisterRes;
import com.airline.service.AccountRegister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/member")
public class RegisterController {
    @Resource
    private AccountRegister accountRegister;

    @RequestMapping(value = "/registerByMail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public RegisterRes registerByMail (@RequestBody MailRegister mailRegister) {
        RegisterRes res = new RegisterRes();
        boolean verifyResult = verifyParam(mailRegister);
        if (verifyResult) {
            try {
                if (accountRegister.isUnique(mailRegister.getMail())) {
                    accountRegister.preRegMail(mailRegister);
                    res.setAuth(1);
                    res.setCode(0);
                    return res;
                } else {
                    res.setAuth(-1);
                    res.setCode(1012);
                    return res;
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.setAuth(-2);
                res.setCode(2000);
                return res;
            }
        }
        else {
            res.setAuth(-1);
            res.setCode(1000);
            return res;
        }
    }

    private boolean verifyParam (MailRegister mailRegister) {
        try {
            return mailRegister.getMail() != null && mailRegister.getPassword() != null && mailRegister.getPlatform() != null;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
