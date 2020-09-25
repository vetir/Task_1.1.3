package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService test1 = new UserServiceImpl();

        test1.createUsersTable();

        test1.saveUser("Вася", "Сыроежкин", (byte)21);
        test1.saveUser("Петя", "Яблочкин", (byte)11);
        test1.saveUser("Боря", "Иванов", (byte)15);
        test1.saveUser("Антон", "Кравченко", (byte)17);

        test1.getAllUsers().forEach(System.out::println);

        test1.removeUserById(13);

        /*test1.cleanUsersTable();

        test1.dropUsersTable();*/
    }
}
