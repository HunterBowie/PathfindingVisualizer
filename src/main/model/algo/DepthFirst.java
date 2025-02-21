package model.algo;

import model.Graph;

/**
 * Repersents a Depth First pathfinding algorithm
 **/
public class DepthFirst extends DepthBreadth {

    // EFFECTS: creates a DepthFirst algorithm with a given graph
    public DepthFirst(Graph graph) {
        super(graph, true);
    }
}