package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class ReadWriteJsonTest {

    @Test
    public void testConstructor() {
        ReadWriteJson readWrite = new ReadWriteJson();
        assertEquals("./data/", readWrite.getPath());
    }

    @Test
    public void testReadInvalidFile() {
        try {
            ReadWriteJson.readJson("imaginary.json");
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReadTestFile() {
        try {
            JSONObject obj = ReadWriteJson.readJson("readTest.json");
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            
            List<Integer> listFromJson = new ArrayList<>();
            JSONArray loadedArray = obj.getJSONArray("list");
            for (Object item : loadedArray) {
                listFromJson.add((Integer)item);
            }
            assertEquals(list, listFromJson);
        } catch (IOException e) {
            fail(e.toString());
        }
        
    }


    @Test
    public void testWriteInvalidFile() {
        try {
            ReadWriteJson.writeJson(new JSONObject(), "/my\0illegal:fileName.json");
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriteTestFile() {
        try {
            ReadWriteJson.writeJson(new JSONObject(), "writeTest.json");
        } catch (FileNotFoundException e) {
            fail(e.toString());
        }
    }
    
}
