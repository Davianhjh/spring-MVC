package com.airline.controller;

import com.airline.entity.AccountData;
import com.airline.entity.VerifyRegister;
import com.airline.response.RegVerifyRes;
import com.airline.service.AccountVerify;
import com.airline.service.DeleteVerify;
import com.airline.tools.UTCTimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/member")
public class VerifyRegController {

    @Resource
    private AccountVerify accountVerify;

    @Resource
    private DeleteVerify deleteVerify;

    @RequestMapping(value = "/verifyMail", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public RegVerifyRes verifyMail (@RequestParam("verifyCode") String verifyCode, @RequestParam("platform") String platform) {
        RegVerifyRes res = new RegVerifyRes();
        String utcTimeStr = UTCTimeUtil.getUTCTimeStr();
        VerifyRegister verifyRegister = new VerifyRegister();
        verifyRegister.setVerifyCode(verifyCode);
        verifyRegister.setPlatform(platform);
        verifyRegister.setNowUTC(utcTimeStr);
        try {
            AccountData accountData = accountVerify.verify(verifyRegister);
            if (accountData != null) {
                accountVerify.create(accountData);
                deleteVerify.deleteVerifyInfo(verifyRegister);
                res.setAuth(1);
                res.setCode(0);
                res.setVerify(1);
                return res;
            } else {
                res.setAuth(-1);
                res.setCode(1014);
                return res;                                     // verify not correct
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setAuth(-2);
            res.setCode(2000);
            return res;                                         // server error
        }
    }

    @RequestMapping(value = "/verifyTel", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public RegVerifyRes verifyTel (@RequestParam("verifyCode") String verifyCode, @RequestParam("platform") String platform) {
        RegVerifyRes res = new RegVerifyRes();
        String utcTimeStr = UTCTimeUtil.getUTCTimeStr();
        VerifyRegister verifyRegister = new VerifyRegister();
        verifyRegister.setVerifyCode(verifyCode);
        verifyRegister.setPlatform(platform);
        verifyRegister.setNowUTC(utcTimeStr);
        try {
            AccountData accountData = accountVerify.verify(verifyRegister);
            if (accountData != null) {
                accountVerify.create(accountData);
                deleteVerify.deleteVerifyInfo(verifyRegister);
                res.setAuth(1);
                res.setCode(0);
                res.setVerify(1);
                return res;
            } else {
                res.setAuth(-1);
                res.setCode(1014);
                return res;                                     // verify not correct
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setAuth(-2);
            res.setCode(2000);
            return res;                                         // server error
        }
    }
}
