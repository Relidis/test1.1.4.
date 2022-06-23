package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {
    public static final String userName = "root";
    private static final String dbName = "test1.1.3";
    public static final String password = "root";
    private static final String hostName = "localhost";
   // private static SessionFactory sessionFactory;

  /*  public static Connection getConnection() throws SQLException {
        return getConnection(HOST_NAME, DB_NAME, USER, PASSWORD);
    }
*/
    public static Connection getConnection()
            throws SQLException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?allowPublicKeyRetrieval=true&useSSL=false";
        return DriverManager.getConnection(connectionURL, userName, password);
    }


/*
        public static SessionFactory getSessionFactory () {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration().configure();
                    configuration.addAnnotatedClass(User.class);
                    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                    sessionFactory = configuration.buildSessionFactory(builder.build());

                } catch (Exception e) {
                    System.out.println("Исключение!" + e);
                }
            }
            return sessionFactory;
        }

 */
    }

