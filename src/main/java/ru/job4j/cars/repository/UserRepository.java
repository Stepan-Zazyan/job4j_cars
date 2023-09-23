package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.cars.model.User;

import org.hibernate.query.Query;
/*import javax.persistence.Query;*/
import java.util.*;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     *
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     *
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User SET name = :fName WHERE id = :fId")
                    .setParameter("fName", "new name")
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Удалить пользователя по id.
     *
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     *
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        List<User> result = new ArrayList<>();
        try {
            result = session.createQuery("from User order by id").list();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return result;
    }

    /**
     * Найти пользователя по ID
     *
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        Optional<User> user = Optional.empty();
        try {
            Query<User> query = session
                    .createQuery("from User as i where i.id = :fId", User.class);
            query.setParameter("fId", userId);
            user = query.uniqueResultOptional();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    /**
     * Список пользователей по login LIKE %key%
     *
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        List<User> list = new ArrayList<>();
        try {
            Query<User> query = session
                    .createQuery("from User as i where i.name LIKE :fName", User.class);
            query.setParameter("fName", key);
            list = query.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return list;
    }

    /**
     * Найти пользователя по login.
     *
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Optional<User> user = Optional.empty();
        try {
            Query<User> query = session
                    .createQuery("from User as i where i.name = :fName", User.class);
            query.setParameter("fName", login);
            user = query.uniqueResultOptional();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }
}