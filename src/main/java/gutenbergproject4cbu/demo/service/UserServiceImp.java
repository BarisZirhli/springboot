package gutenbergproject4cbu.demo.service;

import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.model.Role;
import gutenbergproject4cbu.demo.model.User;
import gutenbergproject4cbu.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {


    @Autowired
    private final UserRepository userRepository;

    
    
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getUserLastname(), userDTO.getEmail(), userDTO.getPassword());
        user.setId(UUID.randomUUID().toString());
        Role role = new Role();
        role.setRoleName("USER");

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);

        userRepository.delete(user);
    }

    @Override
    public User findUser(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Override
    public boolean existUserwithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }
    
    

}
