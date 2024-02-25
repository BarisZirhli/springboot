package gutenbergproject4cbu.demo.service;

import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.exception.BookException;
import gutenbergproject4cbu.demo.model.Book;
import gutenbergproject4cbu.demo.model.User;
import java.util.List;

public interface UserService {

    void saveUser(UserDTO user);

    void deleteUser(String email);

    User findUser(String userID);

    boolean existUserwithEmail(String email);

    User findUserByEmail(String email);
    
    void addFavoriteBook(Long bookId,String userId)throws BookException;
    
    List<Book> showBookList(String userId)throws BookException;


}
