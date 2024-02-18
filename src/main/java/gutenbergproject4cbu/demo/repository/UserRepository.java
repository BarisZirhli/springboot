package gutenbergproject4cbu.demo.repository;

import gutenbergproject4cbu.demo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public boolean existsByEmail(String email);

<<<<<<< HEAD
    public Optional<User> findByEmail(String email);
=======
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
>>>>>>> af5da07eb8f7d5e6b2c6769452f406ef468d3489

}
