package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    // jdbc:mysql://localhost:3306
    /*public static Connection getConectJdbs() {

            String url = "jdbc:mysql://localhost:3306/schema_name&useSSL=false";
            String username = "root";
            String password = "bcnbyf";
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("Connection to ProductDB succesfull!");
            }

        catch(SQLException | ClassNotFoundException ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return conn;
    }*/
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_bd?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String DB_NAME = "root";
    private static final String DB_PASSWORD = "bcnbyf";

    public static Connection getConnectionJDBC() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_NAME,DB_PASSWORD);
            System.out.println();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
