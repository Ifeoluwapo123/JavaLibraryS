package utilities;

import enums.BookCategories;
import enums.Sorting;
import jsonParser.JsonFileReader;
import models.Book;
import models.Person;
import java.util.*;

public class BookStore{
    private static List<Book> books = JsonFileReader.parseJsonFile("books.json");;
    public  static  int bookId = books.size();
    private static Display<Book, Object, Object> display = new Display<>();

    public static List<Book> getAllBooks(){
        return books;
    }

    public static void updateBooks(Book book){
        List<Book> allBooks = getAllBooks();
        allBooks.add(book);
        books = allBooks;
    }

    public static List<Book> sortBookBy(Person person, String params){
        if(person.getRole().equalsIgnoreCase("librarian")){
            Sorting sort = validateInputsToSort(params);
            if(sort == null) return null;

            List sortedBook = getAllBooks();
            Comparator<Book> nameSorter = null;

            switch (sort){
                case PAGE:
                    nameSorter = (a, b) -> {
                        if(a.getPages() > b.getPages()) return 1;
                        else return -1;
                    };
                    break;
                case YEAR:
                    nameSorter = (a, b) -> {
                        if(a.getYear() > b.getYear()) return 1;
                        else return -1;
                    };
                    break;
                case AUTHOR:
                    nameSorter = (a, b) -> a.getAuthor().compareToIgnoreCase(b.getAuthor());
                    break;
                case COUNTRY:
                    nameSorter = (a, b) -> a.getCountry().compareToIgnoreCase(b.getCountry());
                    break;
                case CATEGORY:
                    nameSorter = (a, b) -> a.getCategory().compareToIgnoreCase(b.getCategory());
                    break;
            }

            Collections.sort(sortedBook, nameSorter);
            display.displayBookInformation(sortedBook);
            return sortedBook;
        }else{
            System.out.println("You don't have access to this resource");
            return null;
        }
    }

    public static List<Book> searchBookByCategory(String category){
        BookCategories categories;
        List searchItemLists = new ArrayList();

        try {
            categories = BookCategories.valueOf(category.toUpperCase());
        }catch (IllegalArgumentException error){
            System.out.println("Enter valid Category:\n e.g FICTION, HISTORY, JOURNALS\n" +
                               "\t\tLITERATURE AND PROGRAMMING");
            return null;
        }

        switch(categories){
            case FICTION:
            case HISTORY:
            case JOURNALS:
            case LITERATURE:
            case PROGRAMMING:

                for (Book book: getAllBooks()) {
                    if(book.getCategory().equalsIgnoreCase(category)){
                        searchItemLists.add(book);
                    }
                }

                break;
        }

        display.displayBookInformation(searchItemLists);
        return searchItemLists;

    }

    public static List<Book> searchBookByTitle(String title){
        List searchItemLists = new ArrayList();

        for (Book book: getAllBooks()) {
            if(book.getTitle().equalsIgnoreCase(title)){
                searchItemLists.add(book);
            }
        }

        display.displayBookInformation(searchItemLists);

        return searchItemLists;
    }

    public static List<Book> searchBookByCountryName(String country){
        List searchItemLists = new ArrayList();

        for (Book book: getAllBooks()) {
            if(book.getCountry().equalsIgnoreCase(country)){
                searchItemLists.add(book);
            }
        }

        display.displayBookInformation(searchItemLists);

        return searchItemLists;
    }

    public static List<Book> searchBookByLanguage(String language){
        List searchItemLists = new ArrayList();

        for (Book book: getAllBooks()) {
            if(book.getLanguage().equalsIgnoreCase(language)){
                searchItemLists.add(book);
            }
        }

        display.displayBookInformation(searchItemLists);

        return searchItemLists;
    }

    public static Comparator<Book> comparator = new Comparator<Book>() {
        public int compare(Book book, Book another) {
            return book.getTitle().compareTo(another.getTitle());
        }
    };

    public static List<Book> getBook(String title)  {
        List borrowBook = new ArrayList();

        Book book = new Book(title);
        Collections.sort(getAllBooks(), comparator);

        int index = Collections.binarySearch(getAllBooks(), book, comparator);
        if (index >= 0) {
            book = getAllBooks().get(index);
            borrowBook.add(book);
        }

        display.displayBookInformation(borrowBook);

        return borrowBook;

    }

    @Override
    public String toString() {
        String bookStore = "BookStore\n";

        for (Book book: books) {
            bookStore += book.toString();
        }

        display.displayBookInformation(books);

        return bookStore;
    }

    private static Sorting validateInputsToSort(String data){
        Sorting sort;
        try {
            sort = Sorting.valueOf(data.toUpperCase());
        }catch (IllegalArgumentException error){
            System.out.println("Enter valid Search:\n e.g AUTHOR, PAGE, CATEGORY, COUNTRY ,\n" +
                    "YEAR AND STUDENT");
            return null;
        }

        return sort;
    }

}
