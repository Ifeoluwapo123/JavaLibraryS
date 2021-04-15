package controllers;

import models.Book;
import models.Person;
import services.serviceImplementations.LibrarianImplementation;
import utilities.BookStore;
import java.util.Scanner;

public class BookController {

    public static void searchBook(){
        boolean show = true;
        while(show)
        {
            show =showMenu();
        }
    }

    public static void getRecordsOfBookBorrowed(Person librarian){
        LibrarianImplementation.getBookIssuedRecord(librarian);
    }

    public static void userReturnBookToLibrary(Person librarian, Person person){
        System.out.println("\n"+LibrarianImplementation.returnBook(librarian, person));
    }

    public static void addBookToLibrary(Person librarian){
        Book newBook = LibrarianImplementation.createBook(librarian);
        System.out.println("Newly added book to the store "+BookStore.getBook(newBook.getTitle()));
    }

    /**
     *  Allows user to be able to view books based on categories
     *  @return boolean
     * */
    public static boolean showMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Search By the following Enter 1 for page, 2 for author ....\n");
        System.out.println(" (1)Programming \n (2)Fiction\n (3) Journals\n (4) History\n (q) Quit\n");
        switch (scan.nextLine())
        {
            case "1":
                BookStore.searchBookByCategory("programming");
                return true;
            case "2":
                BookStore.searchBookByCategory("fiction");
                return true;
            case "3":
                BookStore.searchBookByCategory("journals");
                return true;
            case "4":
                BookStore.searchBookByCategory("history");
                return true;

            case "q": return  false;
            default:  return  true;

        }
    }
}
