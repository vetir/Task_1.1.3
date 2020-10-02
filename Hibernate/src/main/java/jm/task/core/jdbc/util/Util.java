package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static SessionFactory sessionFactory = null; //

    public static SessionFactory getSessionFactoryUtil() {

        if (sessionFactory == null) {
            try {
                Properties prop= new Properties();
                prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test_bd?serverTimezone=Europe/Moscow&useSSL=false");
                prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
                prop.setProperty("hibernate.connection.username", "root");
                prop.setProperty("hibernate.connection.password", "bcnbyf");
                prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                prop.setProperty("show_sql", String.valueOf(true)); //If you wish to see the generated sql query
                prop.setProperty("hibernate.hbm2ddl.auto", "create");
                prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
                prop.setProperty("hibernate.hbm2ddl.auto", "update");
                sessionFactory = new Configuration()
                        .addProperties(prop)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

