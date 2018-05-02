package com.airline.dao;

import com.airline.entity.MailRegister;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterDaoImpl extends SqlSessionDaoSupport implements RegisterDao {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
    {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public Integer isMailUnique(String mail) {
        Integer result = getSqlSession().selectOne("com.airline.dao.RegisterDao.isMailUnique", mail);
        getSqlSession().commit();
        getSqlSession().close();
        return result;
    }

    public void preRegMail(MailRegister mailRegister) {
        getSqlSession().insert("com.airline.dao.RegisterDao.preRegMail", mailRegister);
        getSqlSession().commit();
        getSqlSession().close();
    }
}
