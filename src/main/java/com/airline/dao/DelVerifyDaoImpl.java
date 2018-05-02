package com.airline.dao;

import com.airline.entity.VerifyRegister;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DelVerifyDaoImpl extends SqlSessionDaoSupport implements DelVerifyDao {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
    {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public void delVerifyInfo (VerifyRegister verifyRegister) {
        getSqlSession().delete("com.airline.dao.DelVerifyDao.delVerifyInfo", verifyRegister);
        getSqlSession().commit();
        getSqlSession().close();
    }
}
