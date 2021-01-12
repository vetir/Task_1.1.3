package web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String allUsers(ModelMap model) {
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("thisUser", thisUser);
        model.addAttribute("listUsers", userService.getAllUsers());
        model.addAttribute("allRoles", userService.getRole());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping(value = "/createUser")
    public String createUser(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/updateUser/{id}")
    public String updateUser(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", userService.getRole());
        return "updateUser";
    }

    @PostMapping(value = "/updateUser")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit")
    public String editUser(@RequestParam(value = "idEdit", required = false) Long id,
                           @RequestParam(value = "firstNameEdit", required = false) String firstName,
                           @RequestParam(value = "lastNameEdit", required = false) String lastName,
                           @RequestParam(value = "ageEdit", required = false) int age,
                           @RequestParam(value = "emailEdit", required = false) String email,
                           @RequestParam(value = "passwordEdit", required = false) String password,
                           @RequestParam(value = "editRole", required = false) String role,
                           Model model) {

        model.addAttribute("allRoles", userService.getRole());
        User user = userService.getUser(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);

        if (password == null) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(password);
        }

        Set<Role> roleSet = new HashSet<>();
        if (role.contains("USER")){
            roleSet.add(new Role("USER"));
            user.setRoles(roleSet);
        }
        if (role.contains("ADMIN")) {
            roleSet.add(new Role("ADMIN"));
            user.setRoles(roleSet);
        }
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUserById(@RequestParam(value = "idDelete", required = false) Long id) {
        userService.remove(id);
        return "redirect:/admin";
    }
}
