package ui;

import java.util.Scanner;

import model.Graph;
import model.NodeType;
import model.Position;

/**
 * Repersents the console interface for the visualization of the graph.
 **/
public class Visualizer {

    private Graph graph;
    private Scanner input;

    // EFFECTS: creates a new Visualizer
    public Visualizer() {
        graph = new Graph(10, 10, new Position(0, 0), new Position(9, 9));
        input = new Scanner(System.in);
        runVisualizer();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runVisualizer() {
        boolean running = true;
        String command = null;

        renderGraph();

        while (running) {
            command = getCommandInput();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
            renderGraph();
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            Position pos = getPosInput();
            graph.setNode(pos, NodeType.WALL);
        } else if (command.equals("c")) {
            graph.setAllNodes(NodeType.EMPTY);
        } else if (command.equals("s")) {
            Position pos = getPosInput();
            graph.setStartPos(pos);
        } else if (command.equals("e")) {
            Position pos = getPosInput();
            graph.setEndPos(pos);
        } else {
            System.out.println("Please enter valid command :|");
        }
    }

    // EFFECTS: gets input from the user and returns it
    private String getCommandInput() {
        System.out.println("""
        Enter one of the following: q: quit, a: add wall, 
        c: clear walls, s: set start pos, e: set end pos""");
        return input.next();
    }

    // EFFECTS: gets input from the user and returns it
    private Position getPosInput() {
        System.out.println("Enter a position in the form: \"{row},{col}\"");
        String[] raw = input.next().split(",");
        return new Position(Integer.valueOf(raw[0]), Integer.valueOf(raw[1]));
    }

    // EFFECTS: render the graph by printing it to console
    private void renderGraph() {
        for (int row = 0; row < graph.getRows(); row++) {
            String printRow = "";
            for (int col = 0; col < graph.getCols(); col++) {
                Position pos = new Position(row, col);
                String printChar = String.valueOf(graph.getNode(pos).ordinal());
                if (pos.equals(graph.getStartPos())) {
                    printChar = "s";
                } else if (pos.equals(graph.getEndPos())) {
                    printChar = "e";
                }
                printRow = printRow + printChar;
            }
            System.out.println(printRow);
        }
    }

}
