package controllers;

import models.Person;
import services.serviceImplementations.LibrarianImplementation;
import java.util.PriorityQueue;

public class PersonController {
    /**
     *  This demonstrates priorities among Three teacher3. If the number of teachers
     *  is greater than the number of books, their priorities will determine how they will be given the the book.
     *  A message would be sent to those that didn't receive the book and those that received the book;
     * */
    public static void demonstratePriorityAmongTeachers(Person librarian, Person teacher1, Person teacher2, Person teacher3, String title) throws InstantiationException {
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

    /**
     *  This demonstrates priorities among between a teacher and a student. If the number of teachers
     *  is greater than the number of books, their priorities will determine how they will be given the the book.
     *  A message would be sent to those that didn't receive the book and those that received the book;
     * */
    public static void demonstratePriorityAmongTeacherAndStudent(Person librarian, Person teacher, Person student, String title) throws InstantiationException {

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

    /**
     *  This demonstrates priorities among between a junior and a senior student. If the number of teachers
     *  is greater than the number of books, their priorities will determine how they will be given the the book.
     *  A message would be sent to those that didn't receive the book and those that received the book;
     * */
    public static void demonstratePriorityAmongStudents(Person librarian, Person student1, Person student2, String title) throws InstantiationException {

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

    /**
     *  This demonstrates a single Person that wants to borrow book.
     *  Here there is no need for prioritizing, a single person requests for a book then get it
     * */
    public static void demonstrateWithAPerson(Person librarian, Person person, String title){
        if(librarian.getRole().equalsIgnoreCase("Librarian")){

            LibrarianImplementation.makeBookRequest(librarian, person, "Blindness");

            if(person.getRole().equalsIgnoreCase("Student")) person.setLevel("SS3");

            LibrarianImplementation.issueBook(librarian, person);
        }
    }
}
