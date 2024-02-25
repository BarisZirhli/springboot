package gutenbergproject4cbu.demo.service;

import gutenbergproject4cbu.demo.DTO.Bookresponse;

public interface BookService {

    public Bookresponse fetchBooks(String query);
    
    public Bookresponse fetchBooks(Long query);
    
   
    
}
