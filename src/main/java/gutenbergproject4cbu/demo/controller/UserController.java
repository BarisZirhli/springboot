package gutenbergproject4cbu.demo.controller;

import gutenbergproject4cbu.demo.exception.BookException;
import gutenbergproject4cbu.demo.model.Book;
import gutenbergproject4cbu.demo.service.UserService;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/dashboard/addFavoriteBook")
    public String addFavoriteBook(@RequestParam(value = "bookId") Long bookId,
            @RequestParam(value = "userId") String userId) throws BookException {
        userService.addFavoriteBook(bookId, userId);

        return "redirect:/users/dashboard";
    }

    @GetMapping("/bookshelf/{userId}")
    public String showBookshelf(@PathVariable String userId, Model model) throws BookException {

        List<Book> bookList = userService.showBookList(userId);
        model.addAttribute("BookList", bookList);

        return "BookShelf";
    }

}
