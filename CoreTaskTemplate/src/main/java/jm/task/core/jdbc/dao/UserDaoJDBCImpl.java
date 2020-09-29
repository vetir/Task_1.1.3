
package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }


    Connection connection = Util.getConnectionJDBC();

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50), " +
                    "lastName VARCHAR (50), " +
                    "age INT NOT NULL" +
                    ") ENGINE=InnoDB;");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("База данных создана");
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {

        }

        System.out.println("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
        Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age +")");

        } catch (SQLException e) {
        }

        System.out.println("Строка с введенными данным добавлена в таблицу");
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE id =" + id);
            System.out.println("Строка с данным id удалена");
        } catch (SQLException e) {

        }


    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException e) {

        }

        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("DELETE FROM users");
            System.out.println("Таблица очищена");

        } catch (SQLException e) {

        }
    }
}

