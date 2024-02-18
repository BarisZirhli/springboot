package gutenbergproject4cbu.demo.model;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class Book {

    private int id;
    private String title;
    private List<Map<String, Object>> authors; // Çünkü bir kitap birden fazla yazar içerebilir
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String> languages;
    private boolean copyright;
    private String media_type;
    private Map<String, String> formats;
    private int download_count;

}
