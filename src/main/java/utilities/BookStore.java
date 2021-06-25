package utilities;

import enums.BookCategories;
import enums.Sorting;
import jsonParser.JsonFileReader;
import models.Book;
import models.Person;
import utilities.Interfaces.BookStoreService;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BookStore{
    private static List<Book> books;
    private static Display<Book, Object, Object> display = new Display<>();

    static {
        try {
            books = JsonFileReader.parseJsonFile("books.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  comparator object used in the get book method, to search a single book
     *  using binary search
     * */
    private static Comparator<Book> comparator = new Comparator<Book>() {
        public int compare(Book book, Book another) {
            return book.getTitle().compareTo(another.getTitle());
        }
    };

    public static List<Book> getAllBooks(){
        return books;
    }

    public static void updateBooks(Book book){
        List<Book> allBooks = getAllBooks();
        allBooks.add(book);
        books = allBooks;
    }

    /**
     *  Sorting books base on page, year, author, country and category
     *  Used by the librarian
     *  @param librarian
     *  @param bookInfo
     *  @return List
     * */
    public static List<Book> sortBookBy(Person librarian, String bookInfo){
        if(librarian.getRole().equalsIgnoreCase("librarian")){
            Sorting sort = validateInputsToSort(bookInfo);
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

    /**
     *  Sorting books by category (fiction, history, journey, literature and programming)
     *  @param category
     *  @return  List
     * */
    BookStoreService searchByCategory = params -> {
        BookCategories categories;
        List searchItemLists = new ArrayList();

        try {
            categories = BookCategories.valueOf(params.toUpperCase());
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

                getAllBooks().stream()
                        .filter(item -> item.getCategory().equalsIgnoreCase(params))
                        .forEach(book -> searchItemLists.add(book));

                break;
        }

        display.displayBookInformation(searchItemLists);
        return searchItemLists;
    };

    BookStoreService searchByTitleLanguageCountry = params -> {
        List searchItemLists = new ArrayList();

        long dataTitle = getAllBooks().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(params)).count();

        long dataLanguage = getAllBooks().stream()
                .filter(item -> item.getLanguage().equalsIgnoreCase(params)).count();

        if(dataTitle > 0){
            getAllBooks()
                    .stream()
                    .filter(item -> item.getTitle().equalsIgnoreCase(params))
                    .forEach(book -> searchItemLists.add(book));
        }else{
            if(dataLanguage > 0)
                getAllBooks()
                        .stream()
                        .filter(item -> item.getLanguage().equalsIgnoreCase(params))
                        .forEach(book -> searchItemLists.add(book));
            else{
                getAllBooks()
                        .stream()
                        .filter(item -> item.getCountry().equalsIgnoreCase(params))
                        .forEach(book -> searchItemLists.add(book));
            }
        }

        display.displayBookInformation(searchItemLists);

        return searchItemLists;
    };

    BookStoreService searchByTitle = param -> {
        List borrowBook = new ArrayList();

        Book book = new Book(param);
        Collections.sort(getAllBooks(), comparator);

        int index = Collections.binarySearch(getAllBooks(), book, comparator);
        if (index >= 0) {
            book = getAllBooks().get(index);
            borrowBook.add(book);
        }

        return borrowBook;
    };

    public BookStoreService searchByTitleLanguageCountry() {
        return searchByTitleLanguageCountry;
    }

    public BookStoreService searchByTitle() {
        return searchByTitle;
    }

    public BookStoreService searchByCategory() {
        return searchByCategory;
    }

    public static String displayAllBooks() {
        String bookStore = "BookStore\n";

        bookStore += getAllBooks().stream().map(book -> book.toString()).collect(Collectors.joining(""));

        display.displayBookInformation2.accept(books);

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

    //testing
    public static void main(String[] args){
        displayAllBooks();
    }

}
