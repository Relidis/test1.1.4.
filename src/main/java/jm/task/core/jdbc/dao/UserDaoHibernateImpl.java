package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();


    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            final String sql = "CREATE TABLE IF NOT EXISTS user (id BIGINT AUTO_INCREMENT, name VARCHAR(50)," +
                    "last_name VARCHAR(50), age TINYINT, PRIMARY KEY (id));";
            session.beginTransaction();
            session.createNativeQuery(sql);
            session.getTransaction().commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users");
            session.getTransaction().commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception ignored) {
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
        try {
        tx.rollback();
        } catch (Exception ignored) {
        }
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction tx = null;
        List<User> userList = new ArrayList<>();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            userList = session.createQuery("from User order by name").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception ignored) {
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception ignored) {
            }
        }
    }
}

