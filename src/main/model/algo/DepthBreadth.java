package model.algo;

import java.util.ArrayList;
import java.util.List;

import exceptions.AlgoFinished;
import exceptions.AlgoOutOfMoves;
import model.Graph;
import model.NodeType;
import model.Position;

public class DepthBreadth extends Algorithm {

    private List<Position> stack;
    private List<List<Position>> paths;
    private boolean isDepth;

    // EFFECTS: creates a DepthFirst algorithm with a given graph
    public DepthBreadth(Graph graph, boolean isDepth) {
    }

    @Override
    protected void highlightPath() {
    }

    // EFFECTS: returns a list of immediate neighbors that can be searched
    private List<Position> getNeighbors(Position current, List<Position> path) {
      
    }

    @Override
    public void step() throws AlgoFinished, AlgoOutOfMoves {
       

}
