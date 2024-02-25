package gutenbergproject4cbu.demo.model;

import java.util.List;
import java.util.Map;

public class Book {

    private Long id;

    private String title;

    private List<Map<String, Object>> authors;

    private Map<String, String> formats;

    private String media_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Map<String, Object>> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Map<String, Object>> authors) {
        this.authors = authors;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public void setFormats(Map<String, String> formats) {
        this.formats = formats;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

}
