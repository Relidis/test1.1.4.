package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/mysq";
    public static Connection connection;
    public static PreparedStatement preparedStatement;

    public static void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Соединение установленно");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            } else {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

