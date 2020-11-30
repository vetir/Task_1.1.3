package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


//    @Autowired
//    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
//        this.userDao = userDao;
//        this.roleDao = roleDao;
//    }

    @Transactional
    @PostConstruct
    public void initialization() {
        Role[] roles = {new Role("ADMIN"), new Role("USER")};
        Set<Role> allRoles = new HashSet<>(Arrays.asList(roles));
        User admin = new User("Vasya", "vasya@mail.ru", "пароль", allRoles);
        userDao.create(admin);
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void remove(Long id) {
        userDao.remove(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public void newRole(Set<Role> roles) {
        roleDao.newRole(roles);
    }

    @Override
    public Set<Role> getRole() {
        return roleDao.getRole();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.getUserByName(s);
    }
}
