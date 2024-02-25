package gutenbergproject4cbu.demo.DTO;

import gutenbergproject4cbu.demo.converter.BookConverter;
import gutenbergproject4cbu.demo.model.User;
import jakarta.persistence.Convert;
import java.util.List;
import java.util.Map;

public class BookDTO {

 

    private int id;
    private Map<String, String> formats;
    private String title;
    @Convert(converter = BookConverter.class)
    private List<Map<String, Object>> authors;
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
