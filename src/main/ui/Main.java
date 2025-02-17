package ui;

import model.Graph;
import model.Node;
import model.NodeType;
import model.Position;
import model.algo.AStar;
import model.algo.Algorithm;

import java.util.Scanner;

/**
 * Repersents the entry point of the program.
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        Graph graph = new Graph(4, 4, new Position(0, 0), new Position(3, 3));
        Algorithm algo = new AStar(graph);
        Scanner scan = new Scanner(System.in);
        graph.setNode(new Position(1, 2), new Node(NodeType.WALL));
        graph.setNode(new Position(2, 2), new Node(NodeType.WALL));
        graph.setNode(new Position(3, 2), new Node(NodeType.WALL));
        // graph.setNode(new Position(5, 4), new Node(NodeType.WALL));
        dummyPrint(graph);
        while (true) {
            scan.next();
            System.out.println("");
            algo.step();
            dummyPrint(graph);
        }


        

    }

    private static void dummyPrint(Graph graph) {
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
