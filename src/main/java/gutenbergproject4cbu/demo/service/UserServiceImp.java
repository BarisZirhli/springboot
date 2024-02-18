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
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
=======
>>>>>>> af5da07eb8f7d5e6b2c6769452f406ef468d3489

@Service
public class UserServiceImp implements UserService, UserDetailsService {


    @Autowired
    private final UserRepository userRepository;

<<<<<<< HEAD
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
=======
    
    
    @Autowired
    private final PasswordEncoder passwordEncoder;
>>>>>>> af5da07eb8f7d5e6b2c6769452f406ef468d3489

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

    public UserServiceImp(@Lazy UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
<<<<<<< HEAD
        this.authenticationManager = authenticationManager;
=======
        
>>>>>>> af5da07eb8f7d5e6b2c6769452f406ef468d3489
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
<<<<<<< HEAD
        Optional<User> user = userRepository.findByEmail(email);
        User u = user.get();
        userRepository.delete(u);
=======
        User user = userRepository.findByEmail(email);

        userRepository.delete(user);
>>>>>>> af5da07eb8f7d5e6b2c6769452f406ef468d3489
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
<<<<<<< HEAD

        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(email);
        User u = user.get();
        LOGGER.info(u.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), authorities);
    }

    public Authentication authenticateUser(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
=======
        return userRepository.findByEmail(email);
>>>>>>> af5da07eb8f7d5e6b2c6769452f406ef468d3489

    }
<<<<<<< HEAD
=======
    
    
>>>>>>> af5da07eb8f7d5e6b2c6769452f406ef468d3489

}
