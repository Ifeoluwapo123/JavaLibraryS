import models.Book;
import models.Person;
import services.serviceImplementations.LibrarianImplementation;
import utilities.BookStore;

import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) throws InstantiationException {
        Person librarian = new Person(29, "Amos James", "Librarian");
        Person teacher1 = new Person(33, "George Tim", "Teacher");
        Person teacher2 = new Person(35, "Sunday Anthony", "Teacher");
        Person teacher3 = new Person(43, "Abraham Edward", "Teacher");

        BookStore.searchBookByCategory("programming");

        LibrarianImplementation.makeBookRequest(librarian, teacher1, "A Doll's House");
        LibrarianImplementation.makeBookRequest(librarian, teacher2, "A Doll's House");
        LibrarianImplementation.makeBookRequest(librarian, teacher3, "A Doll's House");

        System.out.println(teacher1.getRequest());
        System.out.println(teacher2.getRequest());

        if(teacher1.getRequest().equalsIgnoreCase(teacher2.getRequest())
                && teacher2.getRequest().equalsIgnoreCase(teacher3.getRequest())){
            PriorityQueue<Person> priorityQueue = new PriorityQueue<>();
            priorityQueue.add(teacher1);
            priorityQueue.add(teacher2);
            priorityQueue.add(teacher3);

            LibrarianImplementation.issueBook(librarian, priorityQueue);
        }

        Person teacher4 = new Person(30, "James Bond", "Teacher");
        Person student1 = new Person(21, "Adewale Adekunle", "Student");
        String message = student1.setLevel("SS3");

        LibrarianImplementation.makeBookRequest(librarian, teacher4, "Oedipus the King");
        if(message.equals("successful"))
            LibrarianImplementation.makeBookRequest(librarian, student1, "Oedipus the King");


        if(student1.getRequest().equalsIgnoreCase(teacher4.getRequest())){
            PriorityQueue<Person> priorityQueue = new PriorityQueue<>();
            if(message.equals("successful")) priorityQueue.add(student1);
            priorityQueue.add(teacher4);

            LibrarianImplementation.issueBook(librarian, priorityQueue);
        }else if(teacher4.getRequest() != null) LibrarianImplementation.issueBook(librarian, teacher4);
        else if(student1.getRequest() != null)  LibrarianImplementation.issueBook(librarian, student1);

        Person student2 = new Person(19, "Henry Clinton", "Student");
        String mess = student2.setLevel("JSS3");
        if(mess.equals("successful")) LibrarianImplementation.makeBookRequest(librarian, student2, "Oedipus the King");

        if(student1.getRequest().equalsIgnoreCase(student2.getRequest())){
            PriorityQueue<Person> priorityQueue = new PriorityQueue<>();
            if(mess.equals("successful")) priorityQueue.add(student2);
            if(message.equals("successful")) priorityQueue.add(student1);

            LibrarianImplementation.issueBook(librarian, priorityQueue);
        }else if(student1.getRequest() != null) LibrarianImplementation.issueBook(librarian, student1);
        else if(teacher2.getRequest() != null) LibrarianImplementation.issueBook(librarian, student2);


        LibrarianImplementation.getBookIssuedRecord(librarian);

        System.out.println("\n"+LibrarianImplementation.returnBook(librarian,teacher4));
        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, teacher2));
        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, student2));
        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, student1));
        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, teacher1));

        Book newBook = LibrarianImplementation.createBook();
        System.out.println(BookStore.getBook(newBook.getTitle()));

    }
}
