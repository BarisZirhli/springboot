package gutenbergproject4cbu.demo.service;

import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.model.User;

public interface UserService {

    void saveUser(UserDTO user);

    void deleteUser(String email);

    User findUser(String userID);

    boolean existUserwithEmail(String email);

    User findUserByEmail(String email);

}
