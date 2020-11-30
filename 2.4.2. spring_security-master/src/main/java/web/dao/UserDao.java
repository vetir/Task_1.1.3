package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    User getUser(Long id);
    List<User> getAllUsers();
    void update(User user);
    void remove(Long id);
    User getUserByName(String name);
}
