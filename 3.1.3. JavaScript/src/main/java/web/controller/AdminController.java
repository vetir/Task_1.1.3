package web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import web.repo.UserRepo;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUser")
    public User userInfo() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/allRoles")
    public Set<Role> allRoles() {
        return userService.getRole();
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/editUser")
    public ResponseEntity<?> editUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        userService.remove(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUser(id);
    }
}
