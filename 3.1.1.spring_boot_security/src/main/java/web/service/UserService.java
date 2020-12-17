package web.service;

import web.model.Role;
import web.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    void create(User user);
    User getUser(Long id);
    List<User> getAllUsers();
    void update(User user);
    void remove(Long id);
    User getUserByName(String name);
    void newRole(Set<Role> roles);
    Set<Role> getRole();
}
