package services.serviceImplementations;

import models.Book;
import models.Person;
import utilities.BookStore;
import utilities.Display;
import utilities.LibraryRecords;

import java.text.DateFormat;
import java.util.*;

public class LibrarianImplementation {
    private Display<Person, Book, LibraryRecords> display;
    private static Integer defaultDay = 7;

    public String createBook() {
        Scanner scanner = new Scanner(System.in);
        char response = ' ';


        System.out.println("ENTER BOOK CATEGORY: \n");
        String category = scanner.nextLine();

        System.out.println("ENTER BOOK TITLE: \n");
        String title = scanner.nextLine();

        System.out.println("BOOK AUTHOR: \n");
        String author = scanner.nextLine();

        String promptNoOfCopies = "ENTER NUMBER OF COPIES \n";

        int numOfCopies = handlingNumberFormatException(promptNoOfCopies, scanner);

        boolean isBorrow = false;
        do {
            System.out.println("CAN THE BOOK BE BORROWED? y/n: \n");
            response = scanner.next().charAt(0);
            if (response == 'y') {
                isBorrow = true;
            } else if (response == 'n') {
                isBorrow = true;
            } else {
                System.out.println("Try Again! ");
            }
        } while (response != 'y' && response != 'n');

        return "";
    }

    private int handlingNumberFormatException(String prompt, Scanner sc1) {
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

    public static void setBookIssuedRecord(Person person, Book book){
        String records = "";
        if(person.getRole().equalsIgnoreCase("Teacher"))
            records += "Staff ID "+ person.getId()+"\n";
        else records += "Student ID "+ person.getId()+"\n";

        records += "Name: "+ person.getName()+"\n"+
                "Book Title: "+ book.getTitle()+"\n"+
                "Date Issued: "+generateCurrentDate()+" "+getCurrentTime()+"\n"+
                "Return Date: "+getDateToReturnBook();

        LibraryRecords.updateDateRecord(person.getId(),records);
    }

    public static Map<Integer, String> getBookIssuedRecord(){
        return LibraryRecords.getRecords();
    }

    public String returnBook(Person librarian, Person student){
        if(librarian.getRole().equalsIgnoreCase("Librarian")){
            deleteRecord(student);
        }


        return "";
    }

   private String deleteRecord(Person student){
        Map<Integer, String> records;
        String message = ""; int counter = 0;
        records = LibraryRecords.getRecords();

        for (Map.Entry<Integer, String> each: records.entrySet()) {
            if(each.getKey().equals(student.getId()))
            {
                records.remove(student.getId());
                message = "Student record successfully deleted";
                counter+=1;
            }
        }
        if(counter==0) {
            message = "You did not borrow any book!";
        }

        return message;
    }


    public <T> String issueBook(Person librarian, T object){
        String message = "";
        if(librarian.getRole().equalsIgnoreCase("librarian")){
            if(object instanceof Person){
                Person person = (Person) object;
                if(person.getRequest() != null){
                    String request = person.getRequest();
                    String[] bookData = request.split(":");
                    List<Book> bookIssued = BookStore.getBook(bookData[0], bookData[1]);
                    if(bookIssued.size() > 0){
                        message = "successful";
                        // display book to user
                        display = new Display<>();
                        display.displayInformation(person,bookIssued);
                        setBookIssuedRecord(person,(Book) bookIssued);
                    }
                }else{
                    message = "failed";
                    System.out.println("Make a request for a book first");
                }
            }else {
                PriorityQueue que = (PriorityQueue) object;
                Person person = (Person) que.peek();
                if(person.getRequest() != null) {
                    String request = person.getRequest();
                    String[] bookData = request.split(":");
                    List<Book> bookIssued = BookStore.getBook(bookData[0], bookData[1]);
                    setBookIssuedRecord(person, (Book) bookIssued);
                    display = new Display<>();
                    //display for the person
                    display.displayInformation(person, bookIssued);
                    //display others with no book
                    que.remove(que.peek());
                    Iterator<Person> iterator = que.iterator();

                    String result = "Book not issued to\n";
                    while (iterator.hasNext()){
                        result += ((Person) que.poll()).getName()+"\n";
                        System.out.println(result);
                    }
                }else{
                    message = "failed";
                    System.out.println("Make a request for a book first");
                }

                que.clear();
            }
        }else{
            System.out.println("Can't use this method");
        }
        return message;
    }

   public void makeBookRequest(Person person, String title, String author){
        person.setRequest(author+":"+title);
   }

    private static String generateCurrentDate(){
        String date = "";

        DateFormat Date = DateFormat.getDateInstance();
        Calendar calendar = Calendar.getInstance();

        String currentDate = Date.format(calendar.getTime());

        return currentDate;
    }

    private static  String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        return " "+hours+":"+minutes+":"+seconds;
    }

    public static String getDateToReturnBook(){
        String currentDate = generateCurrentDate();
//        String firstSet = currentDate.substring(0,currentDate.indexOf(","));
//        String secondSet = currentDate.substring(currentDate.indexOf(",")+1);
//        String[] str = firstSet.split(" ");
//        int days = Integer.parseInt(str[1]) + numberOfDays;

//        return str[0]+" "+days+","+secondSet;
        String[] str = currentDate.split(" ");
        str[1] = Integer.parseInt(str[1].substring(0, str[1].indexOf(",")))+defaultDay+"";

        return str[0]+" "+str[1]+", "+str[2];

    }



}
