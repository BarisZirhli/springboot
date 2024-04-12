package gutenbergproject4cbu.demo.controller;

import gutenbergproject4cbu.demo.model.Book;
import gutenbergproject4cbu.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    
    @GetMapping("/dashboard")
    
    public String showDashboard() {
        LOGGER.info("Dashboard page accessed.");
        return "dashboard";
    }

   
    @PostMapping("/search")
   
    public String searchBooks(@RequestParam(name = "query", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            List<Book> books = bookService.fetchBooks(query).getResults();
            model.addAttribute("books", books);
        }
        return "dashboard";
    }
}
