package gutenbergproject4cbu.demo.service;

import com.google.gson.Gson;
import gutenbergproject4cbu.demo.DTO.Bookresponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    @Async
    public Bookresponse fetchBooks(String bookname) {
        String apiUrl = "https://gutendex.com/books/?search=" + bookname;
        String response = restTemplate.getForObject(apiUrl, String.class);
        Gson gson = new Gson();
        return gson.fromJson(response, Bookresponse.class);
    }

    @Override
    @Async
    public Bookresponse fetchBooks(Long id) {
        String apiUrl = "https://gutendex.com/books/" + id;
        String response = restTemplate.getForObject(apiUrl, String.class);
        Gson gson = new Gson();
        return gson.fromJson(response, Bookresponse.class);
    }

}
