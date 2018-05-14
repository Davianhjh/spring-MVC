package com.airline;

import com.airline.entity.AccountData;
import com.airline.entity.TelAccount;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class logger {
    @Pointcut("execution(* com.airline.service.MemberLoginImpl.quickAccess(..))")
    private void logInfo(){}

    @Before("logInfo()")
    public void beginLogin(JoinPoint jp) {
        TelAccount telAccount = (TelAccount) jp.getArgs()[0];
        System.out.println(telAccount.getTel());
        System.out.println(telAccount.getTelCountry());
        System.out.println(telAccount.getPassword());
        System.out.println(telAccount.getPlatform());
        System.out.println("login action begins");
        System.out.println(jp.getTarget().getClass());
    }

    @AfterReturning(value = "logInfo()", returning = "accountData")
    public void endLogin(JoinPoint jp, AccountData accountData) {
        System.out.println("quick access ends");
        System.out.println(jp.getTarget().getClass());
        System.out.println(accountData.getUid());
        System.out.println(accountData.getTel());
        System.out.println(accountData.getMail());
    }
}
