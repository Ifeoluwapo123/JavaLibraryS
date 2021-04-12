import models.Person;

import java.util.PriorityQueue;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        //boolean validLevel = "ss".matches("/^[SJ&&S]$d[1-6]/")
       // boolean validLevel = Pattern.compile("^([J]?([S]*)*)[1-3]$").matcher("SJ3").find();
        boolean validLevel = Pattern.compile("^[J]?([S]*)[1-3]$", Pattern.CASE_INSENSITIVE).matcher("sS3").find();
        boolean validLevel1 = Pattern.compile("^[J]?([S]*)[1-3]$").matcher("SS2").find();
        boolean validLevel2 = Pattern.compile("^[J]?([S]*)[1-3]$").matcher("SS1").find();
        boolean validLevel3 = Pattern.compile("^[J]?([S]*)[1-3]$").matcher("JS3").find();
        boolean validLevel4 = Pattern.compile("^[J]?([S]*)[1-3]$").matcher("JS2").find();
        boolean validLevel5 = Pattern.compile("^[J]?([S]*)[1-3]$").matcher("JS1").find();

        boolean validLevel6 = Pattern.compile("^[J]?([S]*)[1-3]$").matcher("SJ3").find();
        boolean validLevel7 = Pattern.compile("^[J]?([S]*)[1-3]$").matcher("JJ3").find();

//        System.out.println(validLevel);
//        System.out.println(validLevel1);
//        System.out.println(validLevel2);
//        System.out.println(validLevel3);
//        System.out.println(validLevel4);
//        System.out.println(validLevel5);
//        System.out.println("FALSE "+validLevel6);
//        System.out.println("FALSE "+validLevel7);
//
        Person teacher = new Person(11, "Randy", "Teacher");
        System.out.println(teacher.getLevel());
//
//        Person teacher2 = new Person(15, "Bandy", "Teacher");
//        System.out.println(teacher2.getLevel());
//
//        Person student1 = new Person(10, "dammy", "Student");
//        student1.setLevel("JS2");
//        System.out.println(student1.getLevel());
//
//        Person student2 = new Person(13, "joy", "Student");
//        student2.setLevel("JS2");
//        System.out.println(student2.getLevel());
//
//        Person student3 = new Person(12, "dam", "Student");
//        student3.setLevel("SS2");
//        System.out.println(student3.getLevel());
//
        PriorityQueue<Person> userPriorityQueue = new PriorityQueue<>();
//        userPriorityQueue.add(student1);
//        userPriorityQueue.add(student2);
//        userPriorityQueue.add(student3);
        userPriorityQueue.add(teacher);
//        userPriorityQueue.add(teacher2);
//
//        Iterator<Person> iterator = userPriorityQueue.iterator();
//
//        while (iterator.hasNext()){
//            System.out.println(userPriorityQueue.poll().toString());
//        }
//
//        // Printing the top element of PriorityQueue
//        System.out.println(userPriorityQueue.peek());
//
//        // Printing the top element and removing it
//        // from the PriorityQueue container
//        System.out.println(pQueue.poll());
//
//        // Printing the top element again
//        System.out.println(pQueue.peek());
        System.out.println(teacher.getClass().getTypeName());
        System.out.println(userPriorityQueue.getClass().getTypeName());

    }

}
