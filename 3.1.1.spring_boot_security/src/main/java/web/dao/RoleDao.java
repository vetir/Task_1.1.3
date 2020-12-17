package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import java.util.Set;

@Repository
public interface RoleDao {
    void newRole(Set<Role> roles);
    Set<Role> getRole();
}
