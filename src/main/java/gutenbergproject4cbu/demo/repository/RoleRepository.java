package gutenbergproject4cbu.demo.repository;

import gutenbergproject4cbu.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<User, Long> {

}
