package controllers;

import models.Person;
import services.serviceImplementations.LibrarianImplementation;
import java.util.Scanner;

public class PersonController {

    public static  void createPerson() throws InstantiationException {
        boolean showSearchMenu = true;
        while(showSearchMenu)
        {
            showSearchMenu = handleCreatePerson();

        }
    }
    /**
     *  Creates People that request for book in the library and loads them to a queue
     *  waiting to be issued a book
     *  @return boolean
     * */
    private static boolean handleCreatePerson() throws InstantiationException {
        Scanner scan = new Scanner(System.in);
        Person person = null;
        System.out.println("*".repeat(90));
        System.out.println("\t".repeat(7)+"WELCOME TO OUR DASHBOARD");
        System.out.println("*".repeat(90));
        System.out.println("Select your Role...\n 1.) TEACHER\n\n 2.) STUDENT\n\n q.) Quit");

        switch (scan.nextLine()) {
            case "1":
                String personId = "ENTER YOUR ID: ";
                int id = LibrarianImplementation.handlingNumberFormatException(personId, scan);
                System.out.println("ENTER NAME: ");
                String name = scan.nextLine();
                String role; String bookTitle;
                role = "Teacher";
                person = new Person(id, name, role);
                System.out.println("Enter the title of the book you want to borrow");
                bookTitle = scan.nextLine();
                person.makeBookRequest(bookTitle);
                return true;
            case "2":
                String studentStringId = "ENTER YOUR ID: ";
                int studentId = LibrarianImplementation.handlingNumberFormatException(studentStringId, scan);
                System.out.println("ENTER  NAME: ");
                String studentName = scan.nextLine();
                role = "Student";
                person = new Person(studentId, studentName, role);
                System.out.println("Enter the title of the book you want to borrow");
                bookTitle = scan.nextLine();
                String className = "ENTER YOUR  ClASS e.g JS1, JS2 ...... SS3: ";
                String studentClass = scan.nextLine();
                person.setLevel(studentClass);
                person.makeBookRequest(bookTitle);
                return true;
            case "q":
                return false;
            default:
                System.out.println("Invalid Option");
                return true;
        }

    }

    /**
     *  Preloads list of People who make request for books in the library
     * */
    public static void handleCreateVersion2() throws InstantiationException {
        Person teacher1 = new Person(33, "George Tim", "Teacher");
        Person teacher2 = new Person(35, "Sunday Anthony", "Teacher");
        Person teacher3 = new Person(43, "Abraham Edward", "Teacher");
        Person student1 = new Person(21, "Adewale Adekunle", "Student");
        Person student2 = new Person(19, "Henry Clinton", "Student");
        Person student3 = new Person(20, "John Aron", "Student");

        student1.setLevel("JS2");
        student2.setLevel("SS2");
        student3.setLevel("JS1");

        teacher1.makeBookRequest("Things Fall Apart");
        teacher3.makeBookRequest("Oedipus the King");
        teacher2.makeBookRequest("Things Fall Apart");
        student1.makeBookRequest("Oedipus the King");
        student2.makeBookRequest("Things Fall Apart");
        student3.makeBookRequest("Blindness");
    }

}
