package web.service;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void create(User user);
    User getUser(Long id);
    List<User> getAllUsers();
    void update(User user);
    void remove(Long id);
    User getUserByMail(String email);
    void newRole(Set<Role> roles);
    Set<Role> getRole();
}
