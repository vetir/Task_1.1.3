package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserDao {
    void create(User user);
    User getUser(Long id);
    List<User> getAllUsers();
    void update(User user);
    void remove(Long id);
    User getUserByName(String name);
}
