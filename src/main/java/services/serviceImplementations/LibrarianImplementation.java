package services.serviceImplementations;

import models.Book;
import models.Person;
import services.Librarian;
import utilities.BookStore;
import utilities.Display;
import utilities.LibraryRecords;
import java.text.DateFormat;
import java.util.*;

public class LibrarianImplementation implements Librarian {
    private static Display<Person, Book, LibraryRecords> display = new Display<>();
    private static Integer defaultDay = 7;

    public static Book createBook(Person librarian) {
        if(librarian.getRole().equalsIgnoreCase("Librarian")){
            Scanner scanner = new Scanner(System.in);
            char response = ' ';

            System.out.println("ENTER BOOK CATEGORY: \n");
            String category = scanner.next();

            System.out.println("ENTER BOOK TITLE: \n");
            String title = scanner.next();

            System.out.println("BOOK AUTHOR: \n");
            String author = scanner.next();

            String promptYearCreated = "ENTER YEAR PUBLISHED \n";
            int year = handlingNumberFormatException(promptYearCreated, scanner);

            String promptNoOfPages = "ENTER NUMBER OF PAGES \n";
            int pages = handlingNumberFormatException(promptNoOfPages, scanner);

            System.out.println("BOOK LANGUAGE: \n");
            String language = scanner.next();

            System.out.println("BOOK COUNTRY: \n");
            String country = scanner.next();

            System.out.println("BOOK IMAGE LINK: \n");
            String imageLink = scanner.next();

            System.out.println("BOOK LINK: \n");
            String link  = scanner.next();

            String promptNoOfCopies = "ENTER NUMBER OF COPIES \n";
            int numOfCopies = handlingNumberFormatException(promptNoOfCopies, scanner);

            Book newBook = new Book(BookStore.getAllBooks().size()+1, numOfCopies, author, country, category,imageLink,
                    language,link, pages, title, year);

            BookStore.updateBooks(newBook);
            return newBook;
        }else{
            System.out.println("You don't have access to this action");
            return  null;
        }
    }

    /**
     *  Used for the create book method, perhaps user enters string instead of a number
     *  @param prompt
     *  @param sc1
     *  @params int
     * */
    private static int handlingNumberFormatException(String prompt, Scanner sc1) {
        int intInput = 0;
        while (true) {
            System.out.println(prompt);
            String stringInput = sc1.next();
            try {
                intInput = Integer.parseInt(stringInput);
                break;
            } catch (NumberFormatException ne) {
                System.out.println("You must enter a number");
            }
        }
        return intInput;
    }

    /**
     *  This method is called everytime a book is issued to save the person
     *  and book information
     *  @param person
     *  @param book
     * */
    public static void setBookIssuedRecord(Person person, List<Book> book){
        String records = "\n";
        if(person.getRole().equalsIgnoreCase("Teacher"))
            records += "Staff ID: "+ person.getId()+"\n";
        else records += "Student ID: "+ person.getId()+"\n";



        records += "Name: "+ person.getName()+"\n"+
                "Book Title: "+ book.get(0).getTitle()+"\n"+
                "Date Issued: "+generateCurrentDate()+" "+getCurrentTime()+"\n"+
                "Return Date: "+getDateToReturnBook()+"\n";

        LibraryRecords.updateDateRecord(person.getId(),records);
    }

    public static Map<Integer, String> getBookIssuedRecord(Person librarian){
        if(librarian.getRole().equalsIgnoreCase("Librarian")){
            display.displayRecordInformation(librarian, LibraryRecords.getRecords());
            return LibraryRecords.getRecords();
        }else{
            System.out.println("You don't have access to this action");
        }

        return null;
    }

    public static String returnBook(Person librarian, Person student){
        String message = "";
        if(librarian.getRole().equalsIgnoreCase("Librarian")){
            message = deleteRecord(student);
        }
        return message;
    }

    private static String deleteRecord(Person person){
        Map<Integer, String> records;
        String message = ""; int counter = 0;
        records = LibraryRecords.getRecords();

        for (Map.Entry<Integer, String> each: records.entrySet()) {
            if(each.getKey().equals(person.getId())) counter++;
        }

        if(counter == 0) {
            message = person.getName()+": You did not borrow any book from us";
        }else{
            records.remove(person.getId());
            message = person.getName()+ ": Book returned, Borrower's record successfully deleted";
        }

        return message;
    }

    /**
     *  Accepts a que of persons objects or a single person as an argument
     *  Gives book to a person or give books to the persons in que based on
     *  the copies of the book we have in the library
     *  if book doesn't go round, then people with highest priority on the
     *  list get book first and others denied (Not issued).
     *  @param librarian
     *  @param object
     *  @return String
     * */
    public static <T> String issueBook(Person librarian, T object){
        String message = "";
        if(librarian.getRole().equalsIgnoreCase("librarian")){
            if(object instanceof Person){
                Person person = (Person) object;

                String request = "";

                try{
                    request = person.getRequest().trim();
                }catch(NullPointerException e){
                    return "failed";
                }

                List<Book> bookIssued = BookStore.getBook(request);;

                if(bookIssued.size() > 0){
                    message = "successful";
                    // display book to user
                    display = new Display<>();
                    display.displayRecordInformation(person,bookIssued);
                    setBookIssuedRecord(person, bookIssued);
                }

            }else {
                PriorityQueue que = (PriorityQueue) object;
                Person person = (Person) que.peek();
                String request = "";

                try{
                    request = person.getRequest().trim();
                }catch(NullPointerException e){
                    return "failed";
                }

                List<Book> bookIssued = BookStore.getBook(request);

                int copiesOfBook = bookIssued.get(0).getNumOfCopies();

                Iterator<Person> iterator = que.iterator();

                String result = "Book not issued to \n";

                while (iterator.hasNext()){
                    copiesOfBook--;
                    display.displayRecordInformation(que.peek(), bookIssued);
                    setBookIssuedRecord(person, bookIssued);
                    ((Person) que.poll()).getName();
                    if(copiesOfBook == 0) break;
                }

                //other persons that doesn't get the book
                iterator = que.iterator();
                int count = 0;
                while (iterator.hasNext()){
                    System.out.println(result+"\t\t"+((Person) que.poll()).getName()+"\n");
                }

                que.clear();
                message = "successful";
            }
        }else{
            System.out.println("Can't use this method");
        }

        return message;
    }

   public static void makeBookRequest(Person librarian, Person person, String title){
        person.setRequest(title);
        System.out.println(person.getName()+", You've successfully made a request for a book");
   }

    private static String generateCurrentDate(){
        String date = "";

        DateFormat Date = DateFormat.getDateInstance();
        Calendar calendar = Calendar.getInstance();

        String currentDate = Date.format(calendar.getTime());

        return currentDate;
    }

    private static String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        return " "+hours+":"+minutes+":"+seconds;
    }

    private static String getDateToReturnBook(){
        String currentDate = generateCurrentDate();
        String[] str = currentDate.split(" ");
        str[1] = Integer.parseInt(str[1].substring(0, str[1].indexOf(",")))+defaultDay+"";

        return str[0]+" "+str[1]+", "+str[2];
    }
}
