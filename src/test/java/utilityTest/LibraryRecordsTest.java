package utilityTest;

import models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utilities.LibraryRecords;

import java.util.HashMap;
import java.util.Map;

public class LibraryRecordsTest {
    Person librarian, teacher, student;

    @BeforeEach
    public void setup() throws InstantiationException {
        librarian = new models.Person(29, "Amos James", "Librarian");
        teacher = new models.Person(43, "Abraham Edward", "Teacher");
        student = new models.Person(21, "Adewale Adekunle", "Student");
    }

    @Test
    public void updateDateAndGetRecord(){
        int key = 5;
        String value = "yyy";
        LibraryRecords.updateDateRecord(key, value);

        Map<Integer, String> mapTest = new HashMap<>();
        Map<Integer, String> mapTest2 = new HashMap<>();
        mapTest2.put(key, "yyy=======PLEASE Return Previous Book=======zzz");
        mapTest.put(key, value);
        assertEquals(mapTest, LibraryRecords.getRecords());

        mapTest.put(key, "zzz");
        LibraryRecords.updateDateRecord(key, "zzz");
        assertNotEquals(mapTest, LibraryRecords.getRecords());
        assertEquals(mapTest2, LibraryRecords.getRecords());
    }
}
