package ui;

import persistence.ReadWriteJson;

/**
 * Repersents the entry point of the program.
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        new Visualizer();
        // System.out.println(ReadWriteJson.readJson("graph1.json"));

    }
}
