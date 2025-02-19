package model.algo;

import model.Graph;

public class BreadthFirst extends DepthBreadth {
    
     // EFFECTS: creates a BreadthFirst algorithm with a given graph
    public BreadthFirst(Graph graph) {
        super(graph, false);
    }
}
