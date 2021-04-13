package services;

import models.Person;
import java.util.Map;

public interface Librarian {
    public static String createBook() {
        return null;
    }

    public static Map<Integer, String> getBookIssuedRecord() {
        return null;
    }

    public static String returnBook(Person librarian, Person student) {
        return null;
    }

    public static <T> String issueBook(Person librarian, T object) {
        return null;
    }

    public static void makeBookRequest(Person librarian, Person person, String title, String author) { }
}
