import controllers.BookController;
import controllers.PersonController;
import models.Person;
import services.serviceImplementations.LibrarianImplementation;
import java.util.*;

public class App {
    public static void main(String[] args) throws InstantiationException {

        Person librarian = new Person(29, "Amos James", "Librarian");

        BookController.searchBook();
        //PersonController.createPerson();
        PersonController.handleCreateVersion2();

        PriorityQueue<Person> priorityQueue = new PriorityQueue<>();

        Person.allRequests.forEach(request -> {
            Person.peopleQueue.forEach(person -> {
                if (request.equals(person.getRequest())) {
                    priorityQueue.add(person);
                }
            });

            if(priorityQueue.size() == 1) LibrarianImplementation.issueBook(librarian, priorityQueue.peek());
            else LibrarianImplementation.issueBook(librarian, priorityQueue);

            priorityQueue.clear();
        });

        BookController.getRecordsOfBookBorrowed(librarian);

        Person.peopleQueue.forEach(person -> BookController.userReturnBookToLibrary(librarian, person));

        //BookController.addBookToLibrary(librarian);

    }
}
