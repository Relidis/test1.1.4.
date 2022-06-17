package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {
    public static final String USER = "root";
    private static final String DB_NAME = "test1.1.3";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/mysq";
    private static final String HOST_NAME = "localhost";
    public static Connection connection;
    public static PreparedStatement preparedStatement;
    private DriverManager driverManager;
    public static Connection getConnection() throws SQLException {
        return getConnection(HOST_NAME, DB_NAME, USER, PASSWORD);
    }
    public static Connection getConnection(String hostName, String dbName, String userName, String password)
            throws SQLException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?allowPublicKeyRetrieval=true&useSSL=false";
        return DriverManager.getConnection(connectionURL, userName, password);
    }
  /*  public Connection getConnection() {
        if (driverManager == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return this.driverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    */
}
