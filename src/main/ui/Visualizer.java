package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import org.json.JSONObject;

import model.Graph;
import model.Node;
import model.NodeType;
import model.Position;
import model.algo.AStar;
import model.algo.Algorithm;
import model.algo.BreadthFirst;
import model.algo.DepthFirst;
import persistence.ParseJson;
import persistence.ReadWriteJson;

/**
 * Repersents the console interface for the visualization of the graph.
 **/
public class Visualizer {

    private Graph graph;
    private Algorithm algo;
    private Scanner scanner;

    // EFFECTS: creates a new Visualizer
    public Visualizer() throws InterruptedException, IOException {
        setUp();
        runVisualizer();
    }

    // MODIFIES: this
    // EFFECTS: set up the visualizer by initializing the graph and algorithm
    private void setUp() throws IOException {
        scanner = new Scanner(System.in);
        System.out.println();
        String createLoadCommand = getLoadCreateInput();
        if (createLoadCommand.equals("c")) {
            createNewGraph();

        } else {
            System.out.println();
            String jsonFile = getInput(
                    "Enter the json file name (with extension)");
            JSONObject data = ReadWriteJson.readJson(jsonFile);
            graph = ParseJson.toGraph(data);
            algo = ParseJson.toAlgorithm(data, graph);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new graph based on user input
    private void createNewGraph() {
        System.out.println();
        try {
            String graphInput = getInput(
                    "Enter graph data in the form \"rows,columns\"");
            String[] splitInput = graphInput.split(",");
            int rows = Integer.valueOf(splitInput[0]);
            int cols = Integer.valueOf(splitInput[1]);
            graph = new Graph(rows, cols, new Position(0, 0), new Position(rows - 1, cols - 1));
            algo = getAlgoInput();

        } catch (NumberFormatException | PatternSyntaxException | ArrayIndexOutOfBoundsException e) {
            printInvalidMessage();
            createNewGraph();
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runVisualizer() throws InterruptedException, FileNotFoundException {
        boolean running = true;
        String command = null;

        while (running) {
            System.out.println();
            renderGraph();
            System.out.println();
            command = getCommandInput();

            if (command.equals("q")) {
                System.out.println();
                quit();
                System.out.println();
                running = false;
            } else {
                processCommand(command);
            }
        }

    }

    // EFFECTS: saves or does not save graph based on user input
    private void quit() throws FileNotFoundException {
        String saveFile = getInput("Would you like to save your data to a file? (y/n)");
        if (saveFile.equals("y")) {
            System.out.println();
            String fileName = getInput("Enter the name of the file the data will be saved in (with extension)");
            JSONObject data = ParseJson.fromGraphAlgo(graph, algo);
            ReadWriteJson.writeJson(data, fileName);

        } else if (saveFile.equals("n")) {
            // pass

        } else {
            printInvalidMessage(saveFile);
            quit();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command input
    private void processCommand(String command) throws InterruptedException {
        if (command.equals("a")) {
            Position pos = getPosInput();
            graph.setNode(pos, new Node(NodeType.WALL));
        } else if (command.equals("c")) {
            graph.setAllNodes(NodeType.EMPTY);
        } else if (command.equals("s")) {
            Position pos = getPosInput();
            graph.setStartPos(pos);
        } else if (command.equals("e")) {
            Position pos = getPosInput();
            graph.setEndPos(pos);
        } else if (command.equals("r")) {
            runAlgorithm();
        } else {
            printInvalidMessage(command);
        }
    }

    // EFFECTS: runs the algorithm and prints the results to the console
    private void runAlgorithm() throws InterruptedException {
        int delay = 200;
        if (algo.getClass() == BreadthFirst.class) {
            delay = 20;
        }
        while (!algo.isFinished()) {
            System.out.println();
            algo.step();
            renderGraph();
            Thread.sleep(delay);
        }
        for (int row = 0; row < graph.getRows(); row++) {
            for (int col = 0; col < graph.getCols(); col++) {
                Node node = graph.getNode(new Position(row, col));
                if (node.getNodeType() != NodeType.WALL) {
                    node.setNodeType(NodeType.EMPTY);
                }
            }
        }
        System.out.println();
        algo = getAlgoInput();
    }

    // EFFECTS: returns user input after stripped and making lower case
    private String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.next().strip().toLowerCase();
    }

    // EFFECTS: returns input from the user
    private String getCommandInput() {
        return getInput("""
                Enter one of the following commands:
                [q] quit
                [a] add wall
                [c] clear walls
                [s] set start pos
                [e] set end pos
                [r] run algorithm""");
    }

    // EFFECTS: returns position input from the user
    private Position getPosInput() {
        try {
            String input = getInput("Enter a position in the form \"row,column\"");
            String[] splitInput = input.split(",");
            return new Position(Integer.valueOf(splitInput[0]), Integer.valueOf(splitInput[1]));
        } catch (NumberFormatException | PatternSyntaxException | ArrayIndexOutOfBoundsException e) {
            printInvalidMessage();
            System.out.println();
            return getPosInput();
        }
    }

    // EFFECTS: prints a generic invalid message with given invalidInput
    private void printInvalidMessage(String invalidInput) {
        System.out.println("Input '" + invalidInput + "' is not valid");
    }

    // EFFECTS: prints a generic invalid message
    private void printInvalidMessage() {
        System.out.println("Input is not valid");
    }

    // EFFECTS: returns either "c" or "l" from user input command
    private String getLoadCreateInput() {
        String input = getInput("""
                Enter one of the following commands:
                [c] create new graph
                [l] load graph from file""");

        if (input.equals("c") || input.equals("l")) {
            return input;
        }
        printInvalidMessage(input);
        System.out.println();
        return getLoadCreateInput();
    }

    // EFFECTS: return algorithm input from the user
    private Algorithm getAlgoInput() {
        String input = getInput("""
                Enter a type of pathfinding algorithm:
                [a] A*
                [d] depth first
                [b] breadth first""");

        if (input.equals("a")) {
            return new AStar(graph);
        }
        if (input.equals("b")) {
            return new BreadthFirst(graph);
        }
        if (input.equals("d")) {
            return new DepthFirst(graph);
        }

        printInvalidMessage(input);

        return getAlgoInput();

    }

    // REQUIRES: this.setUp() must be called
    // EFFECTS: render the graph by printing it to console
    private void renderGraph() {
        for (int row = 0; row < graph.getRows(); row++) {
            String printRow = "";
            for (int col = 0; col < graph.getCols(); col++) {
                Position pos = new Position(row, col);
                String printChar = graph.getNode(pos).toString();
                if (pos.equals(graph.getStartPos())) {
                    printChar = "S";
                } else if (pos.equals(graph.getEndPos())) {
                    printChar = "E";
                }
                printRow = printRow + printChar;
            }
            System.out.println(printRow);
        }
    }

}
