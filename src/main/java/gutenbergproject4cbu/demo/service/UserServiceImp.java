package gutenbergproject4cbu.demo.service;

import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.exception.BookException;
import gutenbergproject4cbu.demo.model.Book;
import gutenbergproject4cbu.demo.model.Role;
import gutenbergproject4cbu.demo.model.User;
import gutenbergproject4cbu.demo.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserServiceImp implements UserService {

  @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookService bookService;

    @Autowired
    @Lazy
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, BookService bookService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookService = bookService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getUserLastname(), userDTO.getEmail(), userDTO.getPassword());
        user.setId(UUID.randomUUID().toString());
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleName("USER");
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user.getRoles().forEach(myRole -> LOGGER.info(user.getUsername() + " has " + myRole.getRoleName()));

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).get();

        if (user == null) {
            userRepository.delete(user);
        }

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

        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public void addFavoriteBook(Long bookId, String userId) throws BookException {
        User user = userRepository.findById(userId).orElse(null);
        Book book = (Book) bookService.fetchBooks(bookId).getResults();

        if (user != null) {
            user.getFavoriteBooks().add(book.getId());
            userRepository.save(user);
        } else {
            throw new BookException("Book or User not Found " + bookId + " " + userId);
        }

    }

    @Override
    public List<Book> showBookList(String userId) throws BookException {
        User user = userRepository.findById(userId).orElse(null);

        List<Book> bookList = new ArrayList<>();
        if (user != null) {
            for (Long book : user.getFavoriteBooks()) {
                bookList.add(bookService.fetchBooks(book).getResults().get(0));
            }
            return bookList;
        } else {
            throw new BookException("User not Found");
        }
    }

}
