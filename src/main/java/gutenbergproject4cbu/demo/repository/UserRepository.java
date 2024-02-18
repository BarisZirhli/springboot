package gutenbergproject4cbu.demo.repository;

import gutenbergproject4cbu.demo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public boolean existsByEmail(String email);

    public Optional<User> findByEmail(String email);

}
