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
 * Repersents tools for reading and writing JSON to/from files
 **/
public class ParseJson {

    // REQUIRES: JSON data must be in the correct format 
    // EFFECTS: returns a Graph using the given JSON data
    public static Graph toGraph(JSONObject data) {
       return null;
    }

    // REQUIRES: JSON data must be in the correct format 
    // EFFECTS: returns a Algorithmn using the given JSON data and graph
    public static Algorithm toAlgorithm(JSONObject data, Graph graph) {
       return null;
    }

    // EFFECTS: returns a JSON object repersenting the given graph and algorithmn
    public static JSONObject fromGraphAlgo(Graph graph, Algorithm algo) {
        return null;
    }
}
