package gutenbergproject4cbu.demo.service;

import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.model.Role;
import gutenbergproject4cbu.demo.model.User;
import gutenbergproject4cbu.demo.repository.UserRepository;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;

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
    public boolean findUserwithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public Authentication authenticateUser(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        try {

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            return authentication;
        } catch (BadCredentialsException e) {

            throw new BadCredentialsException("Invalid credentials", e);
        }
    }
}
