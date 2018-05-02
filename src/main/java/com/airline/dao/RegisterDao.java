package com.airline.dao;

import com.airline.entity.MailAccount;
import com.airline.entity.TelAccount;
import org.apache.ibatis.annotations.Param;

public interface RegisterDao {

    Integer isMailUnique(@Param("mail") String mail);

    Integer isTelUnique(@Param("telCountry") String telCountry, @Param("tel") String tel);

    void preRegMail(MailAccount mailAccount);

    void preRegTel(TelAccount telAccount);
}
