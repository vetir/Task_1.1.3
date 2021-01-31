package web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
