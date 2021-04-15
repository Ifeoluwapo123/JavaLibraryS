package ServiceTest;

import models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import services.serviceImplementations.LibrarianImplementation;

import java.text.DateFormat;
import java.util.Calendar;

public class LibraryTest {

    models.Person librarian;
    models.Person teacher1;
    models.Person teacher2;
    models.Person teacher3;
    models.Person student1;
    models.Person student2;
    models.Person student3;
    models.Person teacher6;

    @BeforeEach
    public void setup() throws InstantiationException {
        librarian = new models.Person(29, "Amos James", "Librarian");
        teacher1 = new models.Person(33, "George Tim", "Teacher");
        teacher2 = new models.Person(35, "Sunday Anthony", "Teacher");
        teacher3 = new models.Person(43, "Abraham Edward", "Teacher");
        student1 = new models.Person(21, "Adewale Adekunle", "Student");
        student2 = new models.Person(19, "Henry Clinton", "Student");
        student3 = new Person(20, "John Aron", "Student");

        teacher6 = new Person(33, "Rebecca Sterling", "Teacher");

        LibrarianImplementation.makeBookRequest(librarian, teacher1, "Things Fall Apart");
        LibrarianImplementation.makeBookRequest(librarian, teacher2, "Things Fall Apart");
        LibrarianImplementation.makeBookRequest(librarian, teacher3, "Things Fall Apart");

        String mess = student1.setLevel("JS3");
        String mess2 = student2.setLevel("SS2");

        LibrarianImplementation.makeBookRequest(librarian, student1, "Oedipus the King");
        LibrarianImplementation.makeBookRequest(librarian, student2, "Oedipus the King");

    }

    @Test
    public void issueBook() {
        String message = LibrarianImplementation.issueBook(librarian, teacher1);
        String message2 = LibrarianImplementation.issueBook(librarian, teacher1);
        String message3 = LibrarianImplementation.issueBook(librarian, teacher1);
        String message4 = LibrarianImplementation.issueBook(librarian, student1);
        String message5 = LibrarianImplementation.issueBook(librarian, student2);
        String message6 = LibrarianImplementation.issueBook(librarian, teacher6);

        assertEquals("successful", message);
        assertEquals("successful", message2);
        assertEquals("successful", message3);

        if(message.equals("successful")){
            assertEquals("successful", message4);
        }else{
            assertEquals("failed", message4);
        }

        if(message.equals("successful")){
            assertEquals("successful", message5);
        }else{
            assertEquals("failed", message5);
        }

        assertEquals("failed", message6);

    }

    @Test
    public void requestBook() {
        assertEquals("Things Fall Apart", teacher1.getRequest());
        assertEquals("Things Fall Apart", teacher2.getRequest());
        assertEquals("Things Fall Apart", teacher3.getRequest());
        assertEquals("Oedipus the King", student1.getRequest());
        assertEquals("Oedipus the King", student2.getRequest());
    }

    @Test
    public void makeRequest() throws InstantiationException {
        Person librarian = new models.Person(29, "Amos James", "Librarian");
        Person teacher1 = new models.Person(33, "George Tim", "Teacher");

        LibrarianImplementation.makeBookRequest(librarian, teacher1, "Things Fall Apart");

        assertEquals("Things Fall Apart", teacher1.getRequest());
        assertEquals(null, librarian.getRequest());

    }

    @Test
    public void generateCurrentDate(){
        String now = MockedGeneratesCurrentDate();
        assertNotNull(MockedGeneratesCurrentDate());
        assertEquals(now, MockedGeneratesCurrentDate());
        assertTrue(now.indexOf(",") != -1);
        assertTrue(now.indexOf(" ") != -1);
    }

    @Test
    public void generateCurrentTime(){
        String now = MockedGetCurrentTime();
        assertNotNull(now);
        assertEquals(now, MockedGetCurrentTime());
        assertTrue(now.indexOf(":") != -1);
        assertTrue(now.indexOf(" ") != -1);
    }

    @Test
    public void getDateToReturnBook(){
        String now = MockGetDateToReturnBook();
        assertNotNull(now);
        assertEquals(now, MockGetDateToReturnBook());
        assertTrue(now.indexOf(" ") != -1);
    }

    private String MockedGeneratesCurrentDate(){
        String date = "";
        DateFormat Date = DateFormat.getDateInstance();
        Calendar calendar = Calendar.getInstance();
        String currentDate = Date.format(calendar.getTime());
        return currentDate;
    }

    private String MockedGetCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        return " "+hours+":"+minutes+":"+seconds;
    }

    private String MockGetDateToReturnBook(){
        String currentDate = MockedGeneratesCurrentDate();
        String[] str = currentDate.split(" ");
        str[1] = Integer.parseInt(str[1].substring(0, str[1].indexOf(",")))+7+"";

        return str[0]+" "+str[1]+", "+str[2];
    }

    @Test
    public void deleteRecord(){
        String mess = LibrarianImplementation.returnBook(librarian, teacher1);
        assertEquals("George Tim: You did not borrow any book from us", mess);

        LibrarianImplementation.issueBook(librarian, teacher1);

        assertEquals("George Tim: Book returned, Borrower's record successfully deleted",
                LibrarianImplementation.returnBook(librarian, teacher1));

    }

}