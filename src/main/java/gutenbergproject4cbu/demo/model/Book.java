package gutenbergproject4cbu.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter
@Getter
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "uuid2")
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID, generator = "uuid2")
    private String id;
    private String name;
    private Date releaseDate;
    private List<String> authorInfos;
    private String categoryID;
    private String title;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<User> users;

}
