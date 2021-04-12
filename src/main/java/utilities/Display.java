package utilities;

import models.Book;
import models.Person;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Display<M,T,E> {

    public  <M, T> void displayInformation(M obj, T obj2) {
       if(obj instanceof Person && obj2 instanceof Book){
           String display = "";
           System.out.println("==============================================\n");

           if(((Person) obj).getRole().equalsIgnoreCase("Teacher"))
               display += "Staff ID "+ ((Person) obj).getId()+"\n";
           else display += "Student ID "+ ((Person) obj).getId()+"\n";
           display += "Name: "+ ((Person) obj).getName()+"\n"+
                      "Book Borrowed: "+ ((Book) obj2).getTitle()+"\n"+
                      "\t\t Book Title: "+((Book) obj2).getAuthor()+"\n"+
                      "\t\t Author Name: "+((Book) obj2).getAuthor()+"\n";

           System.out.println(display);

       }else if(obj instanceof Person && obj2 instanceof LibraryRecords){

       }
    }

    public  static <T> void displayArrayInformation(T elements) {
        if(elements instanceof Person){

        }else{

        }
    }

}
