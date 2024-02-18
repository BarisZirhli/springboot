package gutenbergproject4cbu.demo.controller;

import com.google.gson.Gson;
import gutenbergproject4cbu.demo.DTO.Bookresponse;
import gutenbergproject4cbu.demo.model.Book;
import gutenbergproject4cbu.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
            Gson gson = new Gson();
            Bookresponse bookResponse = gson.fromJson(response, Bookresponse.class);
            List<Book> books = bookResponse.getResults();

            for (Book book : books) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthors().get(0));
                System.out.println();
            }

            model.addAttribute("books", books);
        }
        return "/dashboard";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        LOGGER.info("Dashboard page accessed.");
        return "Dashboard";
    }

}
