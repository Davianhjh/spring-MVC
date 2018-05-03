package com.airline.dao;

import com.airline.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl extends SqlSessionDaoSupport implements LoginDao {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
    {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public LoginData mailLogin(MailAccount mailAccount) {
        LoginData loginData = getSqlSession().selectOne("com.airline.dao.LoginDao.mailLogin", mailAccount);
        getSqlSession().close();
        return loginData;
    }

    public LoginData telLogin(TelAccount telAccount) {
        LoginData loginData = getSqlSession().selectOne("com.airline.dao.LoginDao.telLogin", telAccount);
        getSqlSession().close();
        return loginData;
    }

    public AccountData quickLogin(TelAccount telAccount) {
        AccountData result = getSqlSession().selectOne("com.airline.dao.LoginDao.quickLogin", telAccount);
        getSqlSession().close();
        return result;
    }

    public Integer searchToken(@Param("uid") int uid) {
        Integer result = getSqlSession().selectOne("com.airline.dao.LoginDao.searchToken");
        getSqlSession().close();
        return result;
    }

    public void createToken(TokenData tokenData) {
        getSqlSession().insert("com.airline.dao.LoginDao.createToken");
        getSqlSession().commit();
        getSqlSession().close();
    }

    public void updateToken(TokenData tokenData) {
        getSqlSession().update("com.airline.dao.LoginDao.updateToken");
        getSqlSession().commit();
        getSqlSession().close();
    }

    public void insertQuickInfo(TelAccount telAccount) {
        getSqlSession().insert("com.airline.dao.LoginDao.insertQuickInfo");
        getSqlSession().commit();
        getSqlSession().close();
    }
}
