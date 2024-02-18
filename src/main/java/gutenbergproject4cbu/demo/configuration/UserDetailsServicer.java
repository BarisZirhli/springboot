package gutenbergproject4cbu.demo.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public interface UserDetailsServicer {

    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
}
