package gutenbergproject4cbu.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookServiceImp implements BookService {

    private final RestTemplate restTemplate;

    @Autowired
    public BookServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String fetchBooks(String bookname) {
        String apiUrl = "https://gutendex.com/books/" + bookname;
        return restTemplate.getForObject(apiUrl, String.class);
    }

}
