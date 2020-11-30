package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

//    public EntityManager getEntityManager() {
//        return this.entityManager;
//    }

    @Transactional
    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    @SuppressWarnings("uncheckrd")
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user From User user").getResultList();
    }

    @Transactional
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        entityManager.remove(getUser(id));
    }

    @Transactional
    @Override
    public User getUserByName(String name) {
        return (User) entityManager.createQuery("select user from User user where user.name=:name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
