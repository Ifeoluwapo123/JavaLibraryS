package utilities;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class LibraryRecords {

    private static Map<Integer, String> records = new HashMap<>();

    public static void updateDateRecord(int id, String value){
        int counter = 0;

        if(records.containsKey(id)) counter++;

        if(counter == 0) records.put(id,value);
        else {
            String previousData = records.get(id);
            String newData = previousData+"=======PLEASE Return Previous Book======="+value;
            records.put(id, newData);
        }
    }

    BiFunction<Integer, String, String> updateRecord2 = (id, value)->{
        int counter = 0;

        if(records.containsKey(id)) counter++;

        if(counter == 0){
            records.put(id,value);
            return "Thanks you for borrowing book from us";
        }
        else {
            String previousData = records.get(id);
            String newData = previousData+"=======PLEASE Return Previous Book======="+value;
            records.put(id, newData);
            return "PLEASE Return Previous Book";
        }
    };

    public static Map<Integer, String> getRecords(){
        return records;
    }

    Supplier<Map<Integer, String>> getRecords2(){
        return (Supplier<Map<Integer, String>>) records;
    };

    //testing
    public static void main(String args[]){
        System.out.println(getRecords());
    }

}
