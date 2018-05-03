package com.airline.dao;

import com.airline.entity.AccountData;
import com.airline.entity.TelAccount;
import com.airline.entity.VerifyRegister;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegVerifyDaoImpl extends SqlSessionDaoSupport implements RegVerifyDao {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
    {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public AccountData verifyAccount (VerifyRegister verifyRegister) {
        AccountData accountData = getSqlSession().selectOne("com.airline.dao.RegVerifyDao.verifyAccount", verifyRegister);
        getSqlSession().close();
        return accountData;
    }

    public void createAccount (AccountData accountData) {
        getSqlSession().insert("com.airline.dao.RegVerifyDao.createAccount", accountData);
        getSqlSession().commit();
        getSqlSession().close();
    }

    public AccountData verifyLogin(TelAccount telAccount) {
        AccountData accountData = getSqlSession().selectOne("com.airline.dao.RegVerifyDao.verifyLogin", telAccount);
        getSqlSession().close();
        return accountData;
    }
}
