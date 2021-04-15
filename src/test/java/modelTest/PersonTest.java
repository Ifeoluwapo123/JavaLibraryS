package modelTest;

import models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    Person student, student2, student3;

    @BeforeEach
    public void setup() throws InstantiationException {
        student = new Person(1, "Adele Sunday", "Student");
        student2 = new Person(1, "Goodluck Sunday", "Student");
        student3 = new Person(1, "Yokson Mary", "Student");
    }

    @Test
    public void setLevelOfStudent(){
        String res = student.setLevel("ss1");
        String res1 = student.setLevel("ss2");
        String res2 = student.setLevel("ss3");
        String res3 = student.setLevel("js1");
        String res4 = student.setLevel("js2");
        String res5 = student.setLevel("js3");

        assertEquals("successful", res);
        assertEquals("successful", res1);
        assertEquals("successful", res2);
        assertEquals("successful", res3);
        assertEquals("successful", res4);
        assertEquals("successful", res5);

        String test = student.setLevel("SS1");
        String test1 = student.setLevel("SS2");
        String test2 = student.setLevel("SS3");
        String test3 = student2.setLevel("JS1");
        String test4 = student3.setLevel("JS2");
        String test5 = student.setLevel("JS3");

        assertEquals("successful", test);
        assertEquals("successful", test1);
        assertEquals("successful", test2);
        assertEquals("successful", test3);
        assertEquals("successful", test4);
        assertEquals("successful", test5);

        String test6 = student.setLevel("SJ1");
        String test7 = student.setLevel("SS");
        String test8 = student.setLevel("jj3");
        String test9 = student.setLevel("JJ1");
        String test10 = student.setLevel("2sJ");
        String test11 = student.setLevel("JJ3");

        assertNotEquals("successful", test9);
        assertNotEquals("successful", test10);
        assertNotEquals("successful", test11);
        assertNotEquals("successful", test8);
        assertNotEquals("successful", test7);
        assertNotEquals("successful", test6);

        assertEquals(4,student.getLevel());
        assertNotNull(student.getLevel());

        assertEquals(2, student2.getLevel());
        assertNotNull(student2.getLevel());

        assertEquals(3, student3.getLevel());
        assertNotNull(student3.getLevel());


    }

}
