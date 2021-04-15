package utilities;
import java.util.HashMap;
import java.util.Map;

public class LibraryRecords {

    private static Map<Integer, String> records = new HashMap<>();

    public static void updateDateRecord(int id, String value){
        int counter = 0;

        for (Map.Entry<Integer, String> items : records.entrySet()) {
            if(id == items.getKey()){
                counter++;
            }
        }

        if(counter == 0) records.put(id,value);
        else {
            String previousData = records.get(id);
            String newData = previousData+"=======PLEASE Return Previous Book======="+value;
            records.put(id, newData);
        }
    }

    public static Map<Integer, String> getRecords(){
        return records;
    }

}
