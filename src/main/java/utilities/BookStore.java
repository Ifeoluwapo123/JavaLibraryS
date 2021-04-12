package utilities;

import jsonParser.JsonFileReader;
import models.Book;
import java.io.FileNotFoundException;
import java.util.*;

public class BookStore{
    private static List<Book> books = new ArrayList<>();
    public  static  int bookId = books.size();

    private static void setBooks(){
        books = JsonFileReader.parseJsonFile("books.json");
        Collections.sort(books);
    }

    private static List<Book> getBooks() {
        setBooks();
        return books;
    }

    public static List<Book> sortBookByAuthorName(){
        List sortedBook = getBooks();
        Comparator<Book> nameSorter = (a, b) -> a.getAuthor().compareToIgnoreCase(b.getAuthor());
        Collections.sort(sortedBook, nameSorter);
        return sortedBook;
    }

    public static List<Book> sortBookByCountry(){
        List sortedBook = getBooks();
        Comparator<Book> nameSorter = (a, b) -> a.getCountry().compareToIgnoreCase(b.getCountry());
        Collections.sort(sortedBook, nameSorter);
        return sortedBook;
    }

    public static List<Book> sortBookByYear(){
        List sortedBook = getBooks();

        Comparator<Book> nameSorter = (a, b) -> {
            if(a.getYear() > b.getYear()) return 1;
            else return -1;
        };

        Collections.sort(sortedBook, nameSorter);
        return sortedBook;
    }

    public static List<Book> sortBookByPageNo(){
        List sortedBook = getBooks();

        Comparator<Book> nameSorter = (a, b) -> {
            if(a.getPages() > b.getPages()) return 1;
            else return -1;
        };

        Collections.sort(sortedBook, nameSorter);
        return sortedBook;
    }

    public static List<Book> searchBookByAuthor(String authorName){
        List searchItemLists = new ArrayList();

        for (Book book: getBooks()) {
            if(book.getAuthor().equalsIgnoreCase(authorName)){
                searchItemLists.add(book);
            }
        }

        return searchItemLists;
    }

    public static List<Book> searchBookByTitle(String title){
        List searchItemLists = new ArrayList();

        for (Book book: getBooks()) {
            if(book.getTitle().equalsIgnoreCase(title)){
                searchItemLists.add(book);
            }
        }

        return searchItemLists;
    }

    public static List<Book> searchBookByCountryName(String country){
        List searchItemLists = new ArrayList();

        for (Book book: getBooks()) {
            if(book.getCountry().equalsIgnoreCase(country)){
                searchItemLists.add(book);
            }
        }

        return searchItemLists;
    }

    public static List<Book> searchBookByLanguage(String language){
        List searchItemLists = new ArrayList();

        for (Book book: getBooks()) {
            if(book.getLanguage().equalsIgnoreCase(language)){
                searchItemLists.add(book);
            }
        }

        return searchItemLists;
    }

    public static List<Book> getBook(String author, String title)  {
        List borrowBook = new ArrayList();

        for (Book book: getBooks()) {
            if(book.getAuthor().equalsIgnoreCase(author) && book.getTitle().equalsIgnoreCase(title)){
                borrowBook.add(book);
            }
        }
        return  borrowBook;
    }

    @Override
    public String toString() {
        String bookStore = "BookStore\n";

        for (Book book: books) {
            bookStore += book.toString();
        }

        return bookStore;
    }

}
