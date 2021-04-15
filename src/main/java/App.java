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
        Person student1 = new Person(21, "Adewale Adekunle", "Student");
        Person student2 = new Person(19, "Henry Clinton", "Student");
        Person student3 = new Person(20, "John Aron", "Student");

        BookStore.searchBookByCategory("programming");

        demonstratePriorityAmongTeachers(librarian, teacher1, teacher2, teacher3);

        demonstratePriorityAmongTeacherAndStudent(librarian, teacher2, student1);

        demonstratePriorityAmongStudents(librarian, student2, student3);

        LibrarianImplementation.issueBook(librarian, teacher2);

        LibrarianImplementation.getBookIssuedRecord(librarian);

        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, teacher1));
        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, teacher2));
        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, teacher3));
        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, student3));
        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, student2));
        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, student1));

//        Book newBook = LibrarianImplementation.createBook();
//        System.out.println(BookStore.getBook(newBook.getTitle()));

    }

    public static void demonstratePriorityAmongTeachers(Person librarian, Person teacher1, Person teacher2, Person teacher3) throws InstantiationException {

        LibrarianImplementation.makeBookRequest(librarian, teacher1, "Things Fall Apart");
        LibrarianImplementation.makeBookRequest(librarian, teacher2, "Things Fall Apart");
        LibrarianImplementation.makeBookRequest(librarian, teacher3, "Things Fall Apart");

        if(teacher1.getRequest().equalsIgnoreCase(teacher2.getRequest())
                && teacher2.getRequest().equalsIgnoreCase(teacher3.getRequest())){
            PriorityQueue<Person> priorityQueue = new PriorityQueue<>();
            priorityQueue.add(teacher1);
            priorityQueue.add(teacher2);
            priorityQueue.add(teacher3);

            LibrarianImplementation.issueBook(librarian, priorityQueue);
        }
    }

    public static void demonstratePriorityAmongTeacherAndStudent(Person librarian, Person teacher, Person student) throws InstantiationException {

        String message = student.setLevel("SS2");

        LibrarianImplementation.makeBookRequest(librarian, teacher, "Oedipus the King");
        if(message.equals("successful"))
            LibrarianImplementation.makeBookRequest(librarian, student, "Oedipus the King");


        if(teacher.getRequest().equalsIgnoreCase(student.getRequest())){
            PriorityQueue<Person> priorityQueue = new PriorityQueue<>();
            if(message.equals("successful")) priorityQueue.add(student);
            priorityQueue.add(teacher);

            LibrarianImplementation.issueBook(librarian, priorityQueue);
        }else if(teacher.getRequest() != null) LibrarianImplementation.issueBook(librarian, teacher);
        else if(student.getRequest() != null)  LibrarianImplementation.issueBook(librarian, student);
    }

    public static void demonstratePriorityAmongStudents(Person librarian, Person student1, Person student2) throws InstantiationException {

        String mess = student1.setLevel("JS3");
        String mess2 = student2.setLevel("SS2");
        if(mess.equals("successful")) LibrarianImplementation.makeBookRequest(librarian, student1, "Oedipus the King");
        if(mess2.equals("successful")) LibrarianImplementation.makeBookRequest(librarian, student2, "Oedipus the King");


        if(student1.getRequest().equalsIgnoreCase(student2.getRequest())){
            PriorityQueue<Person> priorityQueue = new PriorityQueue<>();
            if(mess.equals("successful")) priorityQueue.add(student1);
            if(mess2.equals("successful")) priorityQueue.add(student2);

            LibrarianImplementation.issueBook(librarian, priorityQueue);
        }else if(student1.getRequest() != null) LibrarianImplementation.issueBook(librarian, student1);
        else if(student2.getRequest() != null) LibrarianImplementation.issueBook(librarian, student2);
    }

    public static void issueBook(Person librarian, Person person){
        if(librarian.getRole().equalsIgnoreCase("Librarian")){

            LibrarianImplementation.makeBookRequest(librarian, person, "Blindness");

            if(person.getRole().equalsIgnoreCase("Student")) person.setLevel("SS3");

            LibrarianImplementation.issueBook(librarian, person);
        }
    }
}
