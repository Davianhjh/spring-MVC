package com.airline.controller;

import com.airline.entity.*;
import com.airline.response.LoginRes;
import com.airline.service.AccountVerify;
import com.airline.service.DeleteVerify;
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

    @Resource
    private AccountVerify accountVerify;

    @Resource
    private DeleteVerify deleteVerify;

    @Resource
    private TokenData tokenData;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public LoginRes login (@RequestBody TelAccount telAccount) {
        LoginRes res = new LoginRes();
        int verifyResult = verifyParam(telAccount);
        if (verifyResult != -1) {
            LoginData loginData = null;
            if (verifyResult == 1)
                loginData = memberLogin.searchAccount(telAccount);
            else {
                loginData = memberLogin.searchAccount((MailAccount) telAccount);
            }
            if (loginData != null) {
                String token = tokenUtil.createJWT(String.valueOf(loginData.getUid()), loginData.getUserName(), telAccount.getPlatform(), 7 * 24 * 3600 * 1000);
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
        if (verifyQuickParam(telAccount) == 0) {
            AccountData accountData = memberLogin.quickAccess(telAccount);
            if (accountData != null) {
                accountData.setPlatform(telAccount.getPlatform());
                memberLogin.recordQuickAccess(accountData);
                //TODO
                // sms send module
                //
                res.setAuth(1);
                res.setCode(0);
                if (TEXT_SWITCH) res.setVerifyCode(accountData.getVerifyCode());
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

    @RequestMapping(value = "/verifyQuickAccess", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public LoginRes verifyQuick (@RequestBody TelAccount telAccount) {
        LoginRes res = new LoginRes();
        if (verifyQuickParam(telAccount) == 1) {
            AccountData accountData = accountVerify.verify(telAccount);
            if (accountData != null) {
                String token = tokenUtil.createJWT(String.valueOf(accountData.getUid()), accountData.getUserName(), accountData.getPlatform(), 7 * 24 * 3600 * 1000);
                tokenData.setUid(accountData.getUid());
                tokenData.setToken(token);
                tokenData.setCreate(UTCTimeUtil.getUTCTimeStr());
                tokenData.setPlatform(accountData.getPlatform());
                memberLogin.recordToken(tokenData);
                deleteVerify.deleteQuickInfo(telAccount);
                res.setAuth(1);
                res.setCode(0);
                res.setName(accountData.getUserName());
                res.setToken(token);
                return res;
            } else {
                res.setAuth(-1);
                res.setCode(1025);                                // quick access verify not correct
                return res;
            }
        } else {
            res.setAuth(-1);
            res.setCode(1000);                                    // parameter error
            return res;
        }
    }

    private int verifyParam (TelAccount telAccount) {
        if (verifyBaseParam(telAccount)){
            if (verifyTelParam(telAccount)) {
                return 1;
            } else if (verifyMailParam(telAccount)){
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    private static int verifyQuickParam (TelAccount telAccount) {
        try {
            if (telAccount.getTelCountry() != null && telAccount.getTel() != null && telAccount.getPlatform() != null) {
                if (telAccount.getVerifyCode() != null) return 1;
                else return 0;
            }
            return -1;
        } catch (RuntimeException e){
            return -1;
        }
    }

    private static boolean verifyBaseParam (TelAccount telAccount) {
        try {
            return telAccount.getPassword() != null && telAccount.getPlatform() != null;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private static boolean verifyTelParam (TelAccount telAccount) {
        try {
            return telAccount.getTelCountry() != null && telAccount.getTel() != null;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private static boolean verifyMailParam (TelAccount telAccount) {
        try {
            return telAccount.getMail() != null;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
