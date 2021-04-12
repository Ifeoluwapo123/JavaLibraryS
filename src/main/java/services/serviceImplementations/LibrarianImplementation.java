package services.serviceImplementations;

import models.Person;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LibrarianImplementation {

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

    public String createRecord(){
        return "";
    }

    public String updateRecord(){
        return "";
    }

    public String deleteRecord(){
        return "";
    }


    public <T> String issueBook(Person librarian, T object){
        if(librarian.getRole().equalsIgnoreCase("librarian")){
            if(object.getClass().getTypeName().equalsIgnoreCase("models.Person")){
//                Person person = object.peek();
//              priorityQueue.clear();
            }else {

            }
//            if(priority.getClass().getTypeName()==)
//


        }else{
            System.out.println("Can't use this method");
        }
        return  "";
    }

   public String makeBookRequest(Person person, String title, String author){
        person.setRequest(title+":"+author);
        return "";
   }

}
