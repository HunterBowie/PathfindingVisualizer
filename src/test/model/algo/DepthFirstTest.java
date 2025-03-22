package model.algo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Graph;
import model.Node;
import model.NodeType;
import model.Position;

public class DepthFirstTest extends AlgorithmTest {

    private static final List<List<NodeType>> ONE_STEP_NO_WALLS = Arrays.asList(
      Arrays.asList(NodeType.CLOSED, NodeType.OPEN, NodeType.EMPTY, NodeType.EMPTY),
      Arrays.asList(NodeType.OPEN, NodeType.OPEN, NodeType.EMPTY, NodeType.EMPTY),
      Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY),
      Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY)
    );
    private static final List<List<NodeType>> TWO_STEP_NO_WALLS = Arrays.asList(
      Arrays.asList(NodeType.CLOSED, NodeType.OPEN, NodeType.OPEN, NodeType.EMPTY),
      Arrays.asList(NodeType.OPEN, NodeType.CLOSED, NodeType.OPEN, NodeType.EMPTY),
      Arrays.asList(NodeType.OPEN, NodeType.OPEN, NodeType.OPEN, NodeType.EMPTY),
      Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY)
    );
    private static final List<List<NodeType>> ONE_STEP_WALLS = Arrays.asList(
      Arrays.asList(NodeType.CLOSED, NodeType.OPEN, NodeType.EMPTY, NodeType.EMPTY),
      Arrays.asList(NodeType.OPEN, NodeType.WALL, NodeType.WALL, NodeType.EMPTY),
      Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY),
      Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY)
    );

    private static final List<List<NodeType>> TWO_STEP_WALLS = Arrays.asList(
      Arrays.asList(NodeType.CLOSED, NodeType.CLOSED, NodeType.OPEN, NodeType.EMPTY),
      Arrays.asList(NodeType.OPEN, NodeType.WALL, NodeType.WALL, NodeType.EMPTY),
      Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY),
      Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY)
    );

    private static final List<List<NodeType>> ONE_STEP_NO_WALLS_ALT = Arrays.asList(
        Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY),
        Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY, NodeType.EMPTY),
        Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.OPEN, NodeType.OPEN),
        Arrays.asList(NodeType.EMPTY, NodeType.EMPTY, NodeType.OPEN, NodeType.CLOSED)
      );

    private static final List<List<NodeType>> THREE_STEP_WALLS = Arrays.asList(
        Arrays.asList(NodeType.CLOSED, NodeType.OPEN, NodeType.OPEN, NodeType.EMPTY),
        Arrays.asList(NodeType.OPEN, NodeType.CLOSED, NodeType.WALL, NodeType.EMPTY),
        Arrays.asList(NodeType.CLOSED, NodeType.OPEN, NodeType.WALL, NodeType.EMPTY),
        Arrays.asList(NodeType.OPEN, NodeType.OPEN, NodeType.WALL, NodeType.EMPTY)
    );


    private Graph altGraph;
    private Algorithm altAlgo;

    @BeforeEach
    @Override
    public void runBefore() {
        super.runBefore();
        algo = new DepthFirst(graph);
        altGraph = new Graph(4, 4, new Position(3, 3), new Position(0, 0));
        altAlgo = new DepthFirst(altGraph);
    }
    
    @Test
    public void testStepOnceNoWalls() {
        algo.step();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                assertEquals(ONE_STEP_NO_WALLS.get(row).get(col), 
                        algo.getGraph().getAllNodes().get(row).get(col).getNodeType());
            }
        }
    }

    @Test
    public void testStepTwiceNoWalls() {
        algo.step();
        algo.step();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                assertEquals(TWO_STEP_NO_WALLS.get(row).get(col), 
                        algo.getGraph().getAllNodes().get(row).get(col).getNodeType());
            }
        }
    }

    @Test
    public void testStepOnceWalls() {
        graph.setNode(new Position(1, 1), new Node(NodeType.WALL));
        graph.setNode(new Position(1, 2), new Node(NodeType.WALL));
        algo.step();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                assertEquals(ONE_STEP_WALLS.get(row).get(col), 
                        algo.getGraph().getAllNodes().get(row).get(col).getNodeType());
            }
        }
    }
    @Test
    public void testStepTwiceWalls() {
        graph.setNode(new Position(1, 1), new Node(NodeType.WALL));
        graph.setNode(new Position(1, 2), new Node(NodeType.WALL));
        algo.step();
        algo.step();

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                assertEquals(TWO_STEP_WALLS.get(row).get(col), 
                        algo.getGraph().getAllNodes().get(row).get(col).getNodeType());
            }
        }
    }

    @Test
    public void testStepOnceNoWallsAltGraph() {
        altAlgo.step();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                assertEquals(ONE_STEP_NO_WALLS_ALT.get(row).get(col), 
                        altAlgo.getGraph().getAllNodes().get(row).get(col).getNodeType());
            }
        }
    }

    @Test
    public void testStepThreeTimesWalls() {
        graph.setNode(new Position(1, 2), new Node(NodeType.WALL));
        graph.setNode(new Position(2, 2), new Node(NodeType.WALL));
        graph.setNode(new Position(3, 2), new Node(NodeType.WALL));
        algo.step();
        algo.step();
        algo.step();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                assertEquals(THREE_STEP_WALLS.get(row).get(col), 
                        algo.getGraph().getAllNodes().get(row).get(col).getNodeType());
            }
        }
    }


   

    
}
