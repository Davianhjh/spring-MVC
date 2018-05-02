package com.airline.dao;

import com.airline.entity.MailAccount;
import com.airline.entity.TelAccount;
import org.apache.ibatis.annotations.Param;
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

    public Integer isMailUnique(@Param("mail") String mail) {
        Integer result = getSqlSession().selectOne("com.airline.dao.RegisterDao.isMailUnique");
        getSqlSession().close();
        return result;
    }

    public Integer isTelUnique(@Param("telCountry") String telCountry, @Param("tel") String tel) {
        Integer result = getSqlSession().selectOne("com.airline.dao.RegisterDao.isTelUnique");
        getSqlSession().close();
        return result;
    }

    public void preRegMail(MailAccount mailAccount) {
        getSqlSession().insert("com.airline.dao.RegisterDao.preRegMail", mailAccount);
        getSqlSession().commit();
        getSqlSession().close();
    }

    public void preRegTel(TelAccount telAccount) {
        getSqlSession().insert("com.airline.dao.RegisterDao.preRegTel", telAccount);
        getSqlSession().commit();
        getSqlSession().close();
    }
}
