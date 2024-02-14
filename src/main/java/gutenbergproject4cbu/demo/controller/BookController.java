package gutenbergproject4cbu.demo.controller;

import gutenbergproject4cbu.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/dashboard")
    public String searchBooks(@RequestParam(name = "query", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            String response = bookService.fetchBooks(query);
            model.addAttribute("books", response);
        }
        return "Dashboard";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        LOGGER.info("Dashboard page accessed.");
        return "Dashboard";
    }

}
