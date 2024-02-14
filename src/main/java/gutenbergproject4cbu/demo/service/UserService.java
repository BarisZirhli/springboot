package gutenbergproject4cbu.demo.service;

import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.model.User;
import org.springframework.security.core.Authentication;

public interface UserService {

    void saveUser(UserDTO user);

    void deleteUser(String email);
    
    User findUser(String userID);
    
    boolean findUserwithEmail(String email);
    
    Authentication authenticateUser(String email, String password);
    
}
