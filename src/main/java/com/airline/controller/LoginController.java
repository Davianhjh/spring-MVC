package com.airline.controller;

import com.airline.entity.LoginData;
import com.airline.entity.MailAccount;
import com.airline.entity.TelAccount;
import com.airline.entity.TokenData;
import com.airline.response.LoginRes;
import com.airline.service.MemberLogin;
import com.airline.tools.UTCTimeUtil;
import com.airline.tools.tokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/member")
public class LoginController {
    private static final boolean TEXT_SWITCH = true;

    @Resource
    private MemberLogin memberLogin;

    /*
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public LoginRes login (@RequestBody MailAccount mailAccount) {
        LoginRes res = new LoginRes();
        boolean verifyResult = verifyMailParam(mailAccount);
        if (verifyResult) {
            LoginData loginData = memberLogin.searchAccount(mailAccount);
            if (loginData != null) {
                String token = tokenUtil.createJWT(String.valueOf(loginData.getUid()), loginData.getUserName(), mailAccount.getPlatform(), 7 * 24 * 3600 * 1000);
                TokenData tokenData = new TokenData();
                tokenData.setUid(loginData.getUid());
                tokenData.setToken(token);
                tokenData.setCreate(UTCTimeUtil.getUTCTimeStr());
                tokenData.setPlatform(mailAccount.getPlatform());
                memberLogin.recordToken(tokenData);
                res.setAuth(1);
                res.setCode(0);
                res.setName(loginData.getUserName());
                res.setToken(token);
                return res;
            } else {
                res.setAuth(-1);
                res.setCode(1018);                                // user not found
                return res;
            }
        } else {
            res.setAuth(-1);
            res.setCode(1000);                                    // parameter error
            return res;
        }
    }
    */

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public LoginRes login (@RequestBody TelAccount telAccount) {
        LoginRes res = new LoginRes();
        boolean verifyResult = verifyTelParam(telAccount);
        if (verifyResult) {
            LoginData loginData = memberLogin.searchAccount(telAccount);
            if (loginData != null) {
                String token = tokenUtil.createJWT(String.valueOf(loginData.getUid()), loginData.getUserName(), telAccount.getPlatform(), 7 * 24 * 3600 * 1000);
                TokenData tokenData = new TokenData();
                tokenData.setUid(loginData.getUid());
                tokenData.setToken(token);
                tokenData.setCreate(UTCTimeUtil.getUTCTimeStr());
                tokenData.setPlatform(telAccount.getPlatform());
                memberLogin.recordToken(tokenData);
                res.setAuth(1);
                res.setCode(0);
                res.setName(loginData.getUserName());
                res.setToken(token);
                return res;
            } else {
                res.setAuth(-1);
                res.setCode(1018);                                // user not found
                return res;
            }
        } else {
            res.setAuth(-1);
            res.setCode(1000);                                    // parameter error
            return res;
        }
    }

    @RequestMapping(value = "/quickAccess", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public LoginRes quickAccess (@RequestBody TelAccount telAccount) {
        LoginRes res = new LoginRes();
        boolean verifyResult = verifyTelParam(telAccount);
        if (verifyResult) {
            Integer uid = memberLogin.quickAccess(telAccount);
            if (uid != null) {
                telAccount.setUid(uid);
                memberLogin.recordQuickAccess(telAccount);
                //TODO
                // sms send module
                //
                res.setAuth(1);
                res.setCode(0);
                if (TEXT_SWITCH) res.setVerifyCode(telAccount.getVerifyCode());
                return res;
            } else {
                res.setAuth(-1);
                res.setCode(1018);                                // user not found
                return res;
            }
        } else {
            res.setAuth(-1);
            res.setCode(1000);                                    // parameter error
            return res;
        }
    }

    private boolean verifyMailParam (MailAccount mailAccount) {
        try {
            return mailAccount.getMail() != null && mailAccount.getPassword() != null && mailAccount.getPlatform() != null;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private boolean verifyTelParam (TelAccount telAccount) {
        try {
            return telAccount.getTelCountry() != null && telAccount.getTel() != null && telAccount.getPassword() != null && telAccount.getPlatform() != null;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
