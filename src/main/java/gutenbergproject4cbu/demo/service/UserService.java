package gutenbergproject4cbu.demo.service;

import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.model.User;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> af5da07eb8f7d5e6b2c6769452f406ef468d3489

public interface UserService {

    void saveUser(UserDTO user);

    void deleteUser(String email);

    User findUser(String userID);

    boolean existUserwithEmail(String email);

    User findUserByEmail(String email);

}
