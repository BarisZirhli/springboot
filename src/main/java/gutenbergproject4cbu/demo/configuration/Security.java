package gutenbergproject4cbu.demo.configuration;

import gutenbergproject4cbu.demo.controller.BookController;
import gutenbergproject4cbu.demo.model.User;
import gutenbergproject4cbu.demo.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Security implements UserDetailsServicer {

    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserRepository userRepository;

    public Security(@Lazy AuthenticationManager authenticationManager, @Lazy UserRepository userRepository) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        LOGGER.info(user.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public Authentication authenticateUser(String email, String password) {
        UserDetails userDetails = loadUserByEmail(email);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        try {

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            return authentication;
        } catch (BadCredentialsException e) {

            throw new BadCredentialsException("Invalid credentials", e);
        }
    }

}
