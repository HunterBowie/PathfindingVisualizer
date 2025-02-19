package model.algo;

import model.Graph;


public class DepthFirst extends DepthBreadth {

    // EFFECTS: creates a DepthFirst algorithm with a given graph
    public DepthFirst(Graph graph) {
        super(graph, true);
    }
}