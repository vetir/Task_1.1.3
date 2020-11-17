package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void create(User user) {
        getEntityManager().persist(user);
    }

    @Override
    public User getUser(Long id) {
        return getEntityManager().find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return getEntityManager().createQuery("FROM User").getResultList();
    }

    @Override
    public void update(User user) {
        getEntityManager().merge(user);
    }

    @Override
    public void remove(Long id) {
        getEntityManager().createQuery("DELETE FROM User user WHERE user.id =: id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
