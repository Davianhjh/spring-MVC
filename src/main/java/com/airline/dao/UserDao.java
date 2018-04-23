package com.airline.dao;

import com.airline.test.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDao {

    private SqlSessionFactory factory;

    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("../applicationContext.xml");
        factory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
    }

    public void insertUser(User user) {
        SqlSession session = factory.openSession();
        session.insert("namespace.insertUser", user);
        session.commit();
    }
}
