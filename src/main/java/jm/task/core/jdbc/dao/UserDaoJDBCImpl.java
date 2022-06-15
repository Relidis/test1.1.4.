package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
    Util.getConnection();
        preparedStatement = connection.prepareStatement("CREATE TABLE test1.users ( `id` BIGINT NULL," +
                    "`name` VARCHAR(45) NULL," +
                    "`lastName` VARCHAR(45) NULL," +
                    "`age` TINYINT NULL, " +
            "PRIMARY KEY (`id`));");
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() {
        Util.getConnection();
        try {
            preparedStatement = connection.prepareStatement("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("Таблица не удалена");
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Util.getConnection();

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUE (?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Пользователь не сохранен");
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        Util.getConnection();
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM users where id = ?");
        } catch (SQLException e) {
            System.out.println("Пользователь не удален");
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        return null;
    }

 List <User> list = new ArrayList<>();
    {
        try {
            ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM users");
            while(resultSet.next());
            {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println("Пользователь записан");
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        Util.getConnection();
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("Таблица очищена");
            throw new RuntimeException(e);
        }
    }
}
