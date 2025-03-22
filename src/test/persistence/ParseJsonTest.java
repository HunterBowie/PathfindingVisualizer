package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.JsonParseError;
import model.Graph;
import model.NodeType;
import model.Position;
import model.algo.AStar;
import model.algo.Algorithm;
import model.algo.BreadthFirst;
import model.algo.DepthFirst;

public class ParseJsonTest {

    private JSONObject data;

    @BeforeEach
    public void setUp() {
        data = new JSONObject();
        data.put("rows", 4);
        data.put("cols", 5);
        JSONArray array = new JSONArray();
        array.put(1);
        array.put(2);
        data.put("start", array);
        JSONArray array2 = new JSONArray();
        array2.put(3);
        array2.put(4);
        data.put("end", array2);
        data.put("algo", AStar.class.getName());
        JSONArray walls = new JSONArray();
        JSONArray wall1 = new JSONArray();
        wall1.put(1);
        wall1.put(1);
        walls.put(wall1);
        data.put("walls", walls);
    }

    @Test
    public void testConstuctor() {
        new ParseJson();
    }

    @Test
    public void testToGraph() {
        Graph graph = ParseJson.toGraph(data);
        assertEquals(graph.getRows(), 4);
        assertEquals(graph.getCols(), 5);
        assertEquals(graph.getStartPos(), new Position(1, 2));
        assertEquals(graph.getEndPos(), new Position(3, 4));
        assertEquals(graph.getNode(new Position(1, 1)).getNodeType(), NodeType.WALL);
    }

    @Test
    public void testToAlgorithmAStar() {
        Graph graph = ParseJson.toGraph(data);
        assertEquals(graph.getRows(), 4);
        assertEquals(graph.getCols(), 5);
        assertEquals(graph.getStartPos(), new Position(1, 2));
        assertEquals(graph.getEndPos(), new Position(3, 4));
        assertEquals(graph.getNode(new Position(1, 1)).getNodeType(), NodeType.WALL);
        Algorithm algo = ParseJson.toAlgorithm(data, graph);
        assertTrue(algo.getClass().getName() == AStar.class.getName());

    }

    @Test
    public void testToAlgorithmDepthFirst() {
        data.put("algo", DepthFirst.class.getName());
        Graph graph = ParseJson.toGraph(data);
        assertEquals(graph.getRows(), 4);
        assertEquals(graph.getCols(), 5);
        assertEquals(graph.getStartPos(), new Position(1, 2));
        assertEquals(graph.getEndPos(), new Position(3, 4));
        assertEquals(graph.getNode(new Position(1, 1)).getNodeType(), NodeType.WALL);
        Algorithm algo = ParseJson.toAlgorithm(data, graph);
        assertTrue(algo.getClass().getName() == DepthFirst.class.getName());

    }

    @Test
    public void testToAlgorithmBreadthFirst() {
        data.put("algo", BreadthFirst.class.getName());
        Graph graph = ParseJson.toGraph(data);
        assertEquals(graph.getRows(), 4);
        assertEquals(graph.getCols(), 5);
        assertEquals(graph.getStartPos(), new Position(1, 2));
        assertEquals(graph.getEndPos(), new Position(3, 4));
        assertEquals(graph.getNode(new Position(1, 1)).getNodeType(), NodeType.WALL);
        Algorithm algo = ParseJson.toAlgorithm(data, graph);
        assertTrue(algo.getClass().getName() == BreadthFirst.class.getName());

    }

    @Test
    public void testToAlgorithmInvalid() {
        data.put("algo", "invalid name");
        Graph graph = ParseJson.toGraph(data);
        assertEquals(graph.getRows(), 4);
        assertEquals(graph.getCols(), 5);
        assertEquals(graph.getStartPos(), new Position(1, 2));
        assertEquals(graph.getEndPos(), new Position(3, 4));
        assertEquals(graph.getNode(new Position(1, 1)).getNodeType(), NodeType.WALL);
        try {
            ParseJson.toAlgorithm(data, graph);
            fail("Should have thrown JsonParseError");
        } catch (JsonParseError e) {
            // pass
        }

    }

    @Test
    public void testFromGraphAlogrithm() {
        Graph graph = new Graph(13, 15, new Position(0, 5), new Position(9, 8));
        graph.getNode(new Position(2, 3)).setNodeType(NodeType.WALL);
        Algorithm algo = new AStar(graph);

        JSONObject data = ParseJson.fromGraphAlgo(graph, algo);

        assertEquals(data.get("rows"), 13);
        assertEquals(data.get("cols"), 15);
        assertEquals(data.get("algo"), AStar.class.getName());

        JSONArray dataArray = (JSONArray) data.get("start");

        assertEquals(dataArray.get(0), 0);
        assertEquals(dataArray.get(1), 5);

        JSONArray dataArray2 = (JSONArray) data.get("end");

        assertEquals(dataArray2.get(0), 9);
        assertEquals(dataArray2.get(1), 8);

        JSONArray dataArray3 = (JSONArray) data.get("walls");
        JSONArray dataWall = (JSONArray) dataArray3.get(0);

        assertEquals(dataWall.get(0), 2);
        assertEquals(dataWall.get(1), 3);

    }

}
