package com.airline.dao;

import com.airline.entity.MailRegister;
import org.apache.ibatis.annotations.Param;

public interface RegisterDao {

    Integer isMailUnique(@Param("mail") String mail);

    //boolean isTelUnique(@Param("telCountry") String telCountry, @Param("tel") String tel);

    void preRegMail(MailRegister mailRegister);

    //void preRegTel();
}
