package web.dao;

import web.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("roleDao")
public class RoleDaoImp implements RoleDao{

    EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void newRole(Set<Role> roles) {
        roles.forEach(role -> entityManager.persist(role));
    }

    @Transactional
    @Override
    public Set<Role> getRole() {
        Set<Role> roleSet = new HashSet<>();
        List<Role> roleList = entityManager.createQuery("select role from Role role").getResultList();
        roleSet.addAll(roleList);
        return roleSet;
    }
}
