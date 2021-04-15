package utilities;

import models.Book;
import models.Person;

import java.util.List;
import java.util.Map;

public class Display<M,T,E> {

    public  <M, T> void displayRecordInformation(M obj, T obj2) {
       if(obj instanceof Person && obj2 instanceof List){

           String display = "";
           System.out.println("\n==============================================");

           if(((Person) obj).getRole().equalsIgnoreCase("Teacher"))
               display += "Staff ID: "+ ((Person) obj).getId()+"\n";
           else display += "Student ID: "+ ((Person) obj).getId()+"\n";

           display += "Name: "+ ((Person) obj).getName()+"\n"+
                      "Book Borrowed: \n"+
                      "\t\t Title: "+((List<Book>) obj2).get(0).getTitle()+"\n"+
                      "\t\t Name: "+((List<Book>) obj2).get(0).getAuthor()+"\n"+
                      "\t\t Country: "+((List<Book>) obj2).get(0).getCountry()+"\n"+
                      "\t\t Category: "+((List<Book>) obj2).get(0).getCategory()+"\n"+
                      "\t\t Link: "+((List<Book>) obj2).get(0).getLink()+
                      "\t\t Pages: "+((List<Book>) obj2).get(0).getPages()+"\n"+
                      "\t\t Year: "+((List<Book>) obj2).get(0).getYear()+"\n";

           System.out.println(display);

           System.out.println("==============================================\n");
       }else if(obj instanceof Person && obj2 instanceof Map){

           System.out.println("==============================================\n");

           for(Object i: ((Map<Integer, String>) obj2).entrySet()){
               System.out.println(i);
           }

           System.out.println("==============================================\n");
       }
    }

    public void displayBookInformation(List<Book> sortedBooks){
        System.out.println("\n======================================================" +
                               "================= Lists Of Sorted Books " +
                "=======================================================================");

        for (Book book : sortedBooks){
            System.out.println(book+"\n");
        }

        System.out.println("=======================================================================" +
                "=====================================================================================\n");
    }
}
