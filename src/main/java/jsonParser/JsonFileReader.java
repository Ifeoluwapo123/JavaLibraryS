package jsonParser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Book;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.List;

public class JsonFileReader {
    private static String path;
    private static String absolutePath = "src/main/resources/";

    public static List parseJsonFile(String filename){

        JSONParser parser = new JSONParser();
        List<Book> booksList = null;
        path = absolutePath+filename;

        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream stream =new FileInputStream(path);
            TypeReference<List<Book>> listReference = new TypeReference<List<Book>>() {};
            booksList = mapper.readValue(stream,listReference);
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (JsonParseException ex)
        {
            ex.printStackTrace();
        }
        catch (JsonMappingException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return booksList;

    }
}
