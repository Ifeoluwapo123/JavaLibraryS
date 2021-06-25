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
        BookStore store = new BookStore();
        System.out.println("Newly added book to the store "
                +store.searchByTitle().searchByParams(newBook.getTitle()));
    }

    /**
     *  Allows user to be able to view books based on categories
     *  @return boolean
     * */
    public static boolean showMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Search By the following Enter 1 for page, 2 for author ....\n");
        System.out.println(" (1)Programming \n (2)Fiction\n (3) Journals\n (4) History\n (q) Quit\n");
        BookStore bookStore = new BookStore();
        switch (scan.nextLine())
        {
            case "1":
                bookStore.searchByCategory().searchByParams("Programming");
                return true;
            case "2":
                bookStore.searchByCategory().searchByParams("fiction");
                return true;
            case "3":
                bookStore.searchByCategory().searchByParams("journals");
                return true;
            case "4":
                bookStore.searchByCategory().searchByParams("history");
                return true;

            case "q": return  false;
            default:  return  true;

        }
    }
}
