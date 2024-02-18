package gutenbergproject4cbu.demo.DTO;

import gutenbergproject4cbu.demo.model.Book;
import java.util.List;

public class Bookresponse {

    private List<Book> results;
    private int count;
    private String next;
    private String previous;

    public void setResults(List<Book> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Book> getResults() {
        return results;
    }
}
