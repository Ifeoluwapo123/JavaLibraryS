package utilities;
import java.util.HashMap;
import java.util.Map;

public class LibraryRecords {


    private static Map<Integer, String> records = new HashMap<>();

    public static void updateDateRecord(int id, String value){
        records.put(id,value);
    }

    public static Map<Integer, String> getRecords(){
        return records;
    }
}
