package web.dao;

import web.model.Role;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Repository("userDao")
public class UserDaoImp implements UserDao{

    EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
    @SuppressWarnings("unchecked")
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

    @Override
    public User getUserByName(String name) {
        return (User) entityManager.createQuery("select user from User user where user.name=:name")
            .setParameter("name", name).getSingleResult();
    }
}
