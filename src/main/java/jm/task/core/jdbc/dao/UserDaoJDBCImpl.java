package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

 public class UserDaoJDBCImpl implements UserDao {


    public void createUsersTable() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS user (id BIGINT AUTO_INCREMENT, name VARCHAR(50)," +
                "last_name VARCHAR(50), age TINYINT, PRIMARY KEY (id));";
        Connection conn = null;
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute(createTable);
            conn.commit();
            System.out.println("Таблица создана");
        } catch (Exception ex) {
          //  conn.rollback();
            System.out.println("Таблица не создана");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ignored) {
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        String dropTable = "TRUNCATE TABLE users";
        Connection conn = null;
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute(dropTable);
            conn.commit();
            System.out.println("Таблица удалена");
        } catch (Exception ex) {
           // conn.rollback();
            System.out.println("Таблица не удалена");;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ignored) {
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String saveUser = "INSERT INTO users (name, lastName, age) VALUE (?, ?, ?);";
        Connection conn = null;
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement(saveUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            conn.commit();
            System.out.println("Клиент записан");
        } catch (Exception ex) {
         //   conn.rollback();
            System.out.println("Клиент не записан");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ignored) {
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        String removeUserById = "DELETE FROM users where id = ?";
        Connection conn = null;
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute(removeUserById);
            conn.commit();
            System.out.println("Клиент удален");
        } catch (Exception ex) {
            //conn.rollback();
            System.out.println("Клиент не удален");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ignored) {
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String getAllUsers = "SELECT * FROM users";
        Connection conn = null;

        List<User> list = new ArrayList<>();
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(getAllUsers);
                ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM users");
                while (resultSet.next()) ;
                {
                    User user = new User();
                    user.setId((long) resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge((byte) resultSet.getInt("age"));
                    list.add(user);
                }
            } catch (SQLException e) {
                System.out.println("Пользователь не записан");
                throw new RuntimeException(e);
            }
            conn.commit();
        } catch (Exception ex) {
           // conn.rollback();
            System.out.println("Пользователь не записан");;
        } finally {
            try {
                if (conn != null) {

                    conn.close();
                }
            } catch (Exception ignored) {

            }
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        String cleanUsersTable = "DELETE FROM users";
        Connection conn = null;
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute(cleanUsersTable);
            conn.commit();
            System.out.println("Таблица очищена");
        } catch (Exception ex) {
         //   conn.rollback();
            System.out.println("Таблица не очищена");
        } finally {
            try {
                if (conn != null) {

                    conn.close();
                }
            } catch (Exception ignored) {
            }
        }
    }
 }

