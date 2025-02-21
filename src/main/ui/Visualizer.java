package ui;

import java.util.Scanner;

import model.Graph;
import model.Node;
import model.NodeType;
import model.Position;
import model.algo.AStar;
import model.algo.Algorithm;
import model.algo.BreadthFirst;
import model.algo.DepthFirst;

/**
 * Repersents the console interface for the visualization of the graph.
 **/
public class Visualizer {

    private Graph graph;
    private Algorithm algo;
    private Scanner input;

    // EFFECTS: creates a new Visualizer
    public Visualizer() throws InterruptedException {
        graph = new Graph(6, 6, new Position(0, 0),
                new Position(5, 5));
        input = new Scanner(System.in);
        algo = getAlgoInput();
        runVisualizer();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runVisualizer() throws InterruptedException {
        boolean running = true;
        String command = null;

        while (running) {
            System.out.println();
            renderGraph();
            System.out.println();
            command = getCommandInput();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
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
            graph.setAllNodes(NodeType.EMPTY);
            algo = getAlgoInput();
        } else {
            System.out.println("Input '" + command + "' is not valid");
        }
    }

    // EFFECTS: returns user input after stripped and making lower case
    private String getInput(String prompt) {
        System.out.println(prompt);
        return input.next().strip().toLowerCase();
    }

    // EFFECTS: returns input from the user
    private String getCommandInput() {
        return getInput("""
                Enter one of the following cmds: \n[q] quit \n[a] add wall
                [c] clear walls \n[s] set start pos \n[e] set end pos
                [r] run algorithm""");
    }

    // EFFECTS: returns position input from the user
    private Position getPosInput() {
        String input = getInput("Enter a position in the form of \"row,col\"");
        String[] splitInput = input.split(",");
        return new Position(Integer.valueOf(splitInput[0]), Integer.valueOf(splitInput[1]));
    }

    // EFFECTS: return algorithm input from the user
    private Algorithm getAlgoInput() {
        String input = getInput("""
                Enter a type of pathfinding algorithm:
                [a] A* \n[d] depth first \n[b] breadth first""");

        if (input.equals("a")) {
            return new AStar(graph);
        }
        if (input.equals("b")) {
            return new BreadthFirst(graph);
        }
        if (input.equals("d")) {
            return new DepthFirst(graph);
        }

        System.out.println("Input '" + input + "' is not valid");

        return getAlgoInput();

    }

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
