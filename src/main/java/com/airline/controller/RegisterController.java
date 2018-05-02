package com.airline.controller;

import com.airline.entity.MailAccount;
import com.airline.entity.TelAccount;
import com.airline.response.RegisterRes;
import com.airline.service.AccountRegister;
import com.airline.tools.mailSendUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/member")
public class RegisterController {
    private static final boolean TEXT_SWITCH = true;

    @Resource
    private AccountRegister accountRegister;

    @RequestMapping(value = "/registerByMail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public RegisterRes registerByMail (@RequestBody MailAccount mailAccount) {
        RegisterRes res = new RegisterRes();
        boolean verifyResult = verifyMailParam(mailAccount);
        if (verifyResult) {
            try {
                if (accountRegister.isUnique(mailAccount.getMail())) {
                    accountRegister.preRegMail(mailAccount);
                    if (sendVerifyMail(mailAccount.getMail(), mailAccount.getVerifyCode(), mailAccount.getPlatform())) {
                        res.setAuth(1);
                        res.setCode(0);
                        res.setRegister(1);
                        return res;
                    } else {
                        res.setAuth(-1);
                        res.setCode(1013);
                        return res;                               // verify mail fails to send
                    }
                } else {
                    res.setAuth(-1);
                    res.setCode(1012);
                    return res;                                   // mail registered
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.setAuth(-2);
                res.setCode(2000);
                return res;                                       // server error
            }
        }
        else {
            res.setAuth(-1);
            res.setCode(1000);
            return res;                                           // parameter error
        }
    }

    @RequestMapping(value = "/registerByTel", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public RegisterRes registerByTel (@RequestBody TelAccount telAccount) {
        RegisterRes res = new RegisterRes();
        boolean verifyResult = verifyTelParam(telAccount);
        if (verifyResult) {
            try {
                if (accountRegister.isUnique(telAccount.getTelCountry(), telAccount.getTel())) {
                    accountRegister.preRegTel(telAccount);
                    //TODO
                    // sms send module
                    //
                    res.setAuth(1);
                    res.setCode(0);
                    res.setRegister(1);
                    if (TEXT_SWITCH) res.setVerifyCode(telAccount.getVerifyCode());
                    return res;
                } else {
                    res.setAuth(-1);
                    res.setCode(1010);                            // tel registered
                    return res;
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.setAuth(-2);
                res.setCode(2000);                                // server error
                return res;
            }
        } else {
            res.setAuth(-1);
            res.setCode(1000);                                    // parameter error
            return res;
        }
    }

    private boolean verifyMailParam (MailAccount mailAccount) {
        String mailPattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        try {
            return mailAccount.getMail() != null && mailAccount.getPassword() != null && mailAccount.getPlatform() != null && Pattern.matches(mailPattern, mailAccount.getMail());
        } catch (RuntimeException e) {
            return false;
        }
    }

    private boolean sendVerifyMail(String mail, String verifyCode, String platform) {
        String verifyUrl;
        try {
            Properties serverProp = new Properties();
            InputStream in = RegisterController.class.getResourceAsStream("/serverAddress.properties");
            serverProp.load(in);
            in.close();
            verifyUrl = serverProp.getProperty("localhostServer") + "/member/verifyMail?verifyCode=" + verifyCode + "&platform=" + platform;
        } catch (IOException e) {
            e.printStackTrace();
            return false;                                         // server address properties error
        }
        String context = "<p>系统检测到您正在用此邮箱地址在AGiView竞拍平台注册账号，请您不要将此信息透露给其他人，并在30分钟之内点击如下链接完成邮箱验证。如非您本人操作，请忽略此邮件。</p><a href=\"" + verifyUrl + "\">" + verifyUrl + "</a>";
        mailSendUtil mailSendUtil = new mailSendUtil();
        return mailSendUtil.sendHtmlMail(mail, "AGiView账号邮箱验证", context);
    }

    private boolean verifyTelParam (TelAccount telAccount) {
        try {
            return telAccount.getTel() != null && telAccount.getTelCountry() != null && telAccount.getPassword() != null && telAccount.getPlatform() != null;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
