package persistence;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.JsonParseError;
import model.Graph;
import model.NodeType;
import model.Position;
import model.algo.AStar;
import model.algo.Algorithm;
import model.algo.BreadthFirst;
import model.algo.DepthFirst;

/* 
JSON Format:
 * 
 * "rows" must be of type int and be >= 0
 * "cols" must be of type int and be >= 0
 * "start" must be a list of length 2 of type int where each int is >= 0
 * "end" must be a list of length 2 of type int where each int is >= 0
 * "walls" must be a list of list of length 2 of type int where each int is >= 0
 * "algo" must be a string
*/

/**
 * Repersents tools for parsing JSON objects to and from project model data
 **/
public class ParseJson {

    // REQUIRES: JSON data must be in the correct format 
    // EFFECTS: returns a Graph using the given JSON data
    public static Graph toGraph(JSONObject data) {
        int rows = (int) data.get("rows");
        int cols = (int) data.get("cols");
        JSONArray rawStartPos = (JSONArray) data.get("start");
        JSONArray rawEndPos = (JSONArray) data.get("end");
        Position startPos = new Position((int) rawStartPos.get(0), (int) rawStartPos.get(1));
        Position endPos = new Position((int) rawEndPos.get(0), (int) rawEndPos.get(1));
        JSONArray walls = (JSONArray) data.get("walls");

        Graph graph = new Graph(rows, cols, startPos, endPos);

        for (Object rawWall : walls) {
            JSONArray wall = (JSONArray) rawWall;
            int row = (int) wall.get(0);
            int col = (int) wall.get(1);
            graph.getNode(new Position(row, col)).setNodeType(NodeType.WALL);
        }
        return graph;
    }

    // REQUIRES: JSON data must be in the correct format 
    // EFFECTS: returns a Algorithmn using the given JSON data and graph
    public static Algorithm toAlgorithm(JSONObject data, Graph graph) {
        String value = data.get("algo").toString();
        switch (value) {
            case "model.algo.AStar":
                return new AStar(graph);

            case "model.algo.DepthFirst":
                return new DepthFirst(graph);

            case "model.algo.BreadthFirst":
                return new BreadthFirst(graph);

            default:
                throw new JsonParseError("Value " + value + " is not a valid algorithmn");
        }

    }

    // EFFECTS: returns a JSON object repersenting the given graph and algorithmn
    public static JSONObject fromGraphAlgo(Graph graph, Algorithm algo) {
        int rows = graph.getRows();
        int cols = graph.getCols();

        JSONArray startPos = new JSONArray();
        startPos.put(graph.getStartPos().getRow());
        startPos.put(graph.getStartPos().getCol());

        JSONArray endPos = new JSONArray();
        endPos.put(graph.getEndPos().getRow());
        endPos.put(graph.getEndPos().getCol());

        
        JSONArray walls = ParseJson.graphToWalls(graph);

        JSONObject data = new JSONObject();
        data.put("rows", rows);
        data.put("cols", cols);
        data.put("start", startPos);
        data.put("end", endPos);
        data.put("walls", walls);

        data.put("algo", algo.getClass().getName());
        
        return data;
    }

    // EFFECTS: returns a JSONArray object repersenting the given graph
    private static JSONArray graphToWalls(Graph graph) {
        JSONArray walls = new JSONArray();
        for (int row = 0; row < graph.getRows(); row++) {
            for (int col = 0; col < graph.getCols(); col++) {
                if (graph.getNode(new Position(row, col)).getNodeType() == NodeType.WALL) {
                    JSONArray wall = new JSONArray();
                    wall.put(row);
                    wall.put(col);
                    walls.put(wall);
                }
            }
        }
        return walls;
    }

}
