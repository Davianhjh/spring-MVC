package com.airline.dao;

import com.airline.entity.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
    {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public void insertUser (User user) {
        getSqlSession().insert("com.airline.dao.userDao.insertUser", user);
        getSqlSession().commit();
        getSqlSession().close();
    }
}
