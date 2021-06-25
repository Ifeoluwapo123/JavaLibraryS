package utilityTest;

import models.Book;
import models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import services.serviceImplementations.LibrarianImplementation;
import utilities.BookStore;
import java.util.List;

public class BookStoreTest {

    Person librarian, teacher, student;
    BookStore bookStore = new BookStore();
    @BeforeEach
    public void setup() throws InstantiationException {
        librarian = new models.Person(29, "Amos James", "Librarian");
        teacher = new models.Person(43, "Abraham Edward", "Teacher");
        student = new models.Person(21, "Adewale Adekunle", "Student");
    }

    @Test
    public void sortBookBy(){
        BookStore store = new BookStore();
        LibrarianImplementation.makeBookRequest(librarian, teacher, "Things Fall Apart");
        String request = teacher.getRequest().trim();
        List<Book> ls = store.searchByTitle().searchByParams(request);

        assertNotNull(ls);
        assertEquals(1, ls.size());
        assertEquals("Chinua Achebe", ls.get(0).getAuthor());
        assertEquals("Nigeria", ls.get(0).getCountry());
        assertEquals("Journals", ls.get(0).getCategory());
    }

    @Test
    public void searchBookByCategory(){
        List ls = bookStore.searchByCategory().searchByParams("programming");
        assertNotNull(ls);
        assertEquals(7, ls.size());
    }

    @Test
    public void searchBookByCountry(){
        List ls = bookStore.searchByTitleLanguageCountry().searchByParams("Nigeria");
        System.out.println(ls);
        assertNotNull(ls);
        assertEquals(1, ls.size());
    }

    @Test
    public void searchBookByLanguage(){
        List ls = bookStore.searchByTitleLanguageCountry().searchByParams("English");
        assertNotNull(ls);
        assertEquals(29, ls.size());
    }

    @Test
    public void searchBookByTitle(){
        List ls = bookStore.searchByTitle().searchByParams("Things fall apart");
        assertNotNull(ls);
    }

    @Test
    public void getBook(){
        List ls = bookStore.searchByTitle().searchByParams("The Canterbury Tales");
        assertNotNull(ls);
    }

    @Test
    public void sortBook(){
        List ls = BookStore.sortBookBy(librarian, "year");
        assertNotNull(ls);
        assertEquals(107, ls.size());
    }

}
