package utilities.Interfaces;

import models.Book;
import java.util.List;

@FunctionalInterface
public interface BookStoreService {
    List<Book> searchByParams(String params);
}
