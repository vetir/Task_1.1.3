package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }
    SessionFactory sessionFactory = Util.getSessionFactoryUtil();

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50), " +
                    "lastName VARCHAR (50), " +
                    "age INT NOT NULL" +
                    ") ENGINE=InnoDB;").executeUpdate();
        transaction.commit();
        session.close();
        //System.out.println("Таблица создана");
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        transaction.commit();
        session.close();
        //System.out.println("Таблица удалена");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
        /*
        - через createSQLQuery -
        session.createSQLQuery("INSERT INTO users (name, lastName, age) VALUES ('" +
                name +
                "', '" +
                lastName +
                "', " +
                age +")").executeUpdate();
        transaction.commit();
        session.close();*/
        //System.out.println("Пользователь добавлен");
        /*String hql = "insert into User (name, lastName, age) " +
                "select '" + name + "', '" + lastName + "', " + age + "from User";
        */
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.load(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
        //session.createSQLQuery("DELETE FROM users WHERE id =" + id).executeUpdate();
        //System.out.println("Пользователь удален");
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> user = session.createQuery("From " + User.class.getSimpleName()).list();
        session.close();
        return user;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM users").executeUpdate();
        transaction.commit();
        session.close();
        //System.out.println("Таблица очищена");
    }
}
