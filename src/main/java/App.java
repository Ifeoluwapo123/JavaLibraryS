import controllers.BookController;
import controllers.PersonController;
import models.Person;

public class App {
    public static void main(String[] args) throws InstantiationException {

        Person librarian = new Person(29, "Amos James", "Librarian");
        Person teacher1 = new Person(33, "George Tim", "Teacher");
        Person teacher2 = new Person(35, "Sunday Anthony", "Teacher");
        Person teacher3 = new Person(43, "Abraham Edward", "Teacher");
        Person student1 = new Person(21, "Adewale Adekunle", "Student");
        Person student2 = new Person(19, "Henry Clinton", "Student");
        Person student3 = new Person(20, "John Aron", "Student");

        BookController.searchBook();

        PersonController.demonstratePriorityAmongTeachers(librarian, teacher1, teacher2, teacher3, "Things Fall Apart");

        PersonController.demonstratePriorityAmongTeacherAndStudent(librarian, teacher2, student1, "Oedipus the King");

        PersonController.demonstratePriorityAmongStudents(librarian, student2, student3, "The Canterbury Tales");

        PersonController.demonstrateWithAPerson(librarian, teacher2, "Blindness");

        BookController.getRecordsOfBookBorrowed(librarian);

        Person teacher6 = new Person(33, "Rebecca Sterling", "Teacher");

        BookController.userReturnBookToLibrary(librarian, teacher1);
        BookController.userReturnBookToLibrary(librarian, teacher2);
        BookController.userReturnBookToLibrary(librarian, teacher3);
        BookController.userReturnBookToLibrary(librarian, student3);
        BookController.userReturnBookToLibrary(librarian, student2);
        BookController.userReturnBookToLibrary(librarian, student1);
        BookController.userReturnBookToLibrary(librarian, teacher6);

        BookController.addBookToLibrary(librarian);

    }
}
