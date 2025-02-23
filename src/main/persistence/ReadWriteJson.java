package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONObject;

/**
 * Repersents tools for reading and writing JSON to/from files
 **/
public class ReadWriteJson {
    private static String PATH = "./data/";

    public String getPath() {
        return PATH;
    }

    // EFFECTS: returns the json object from given file
    public static JSONObject readJson(String file) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        Stream<String> stream = Files.lines(Paths.get(PATH + file), StandardCharsets.UTF_8);
        stream.forEach(s -> contentBuilder.append(s));
        stream.close();

        return new JSONObject(contentBuilder.toString());
    }

    // EFFECTS: writes the given json object to given file
    public static void writeJson(JSONObject data, String file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(PATH + file));
        writer.print(data);
        writer.close();
    }

}
