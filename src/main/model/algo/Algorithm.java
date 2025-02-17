package model.algo;

import exceptions.AlgoFinished;
import exceptions.AlgoOutOfMoves;
import model.Graph;

/**
 * Repersents a type of pathfinding algorithm
 **/
public abstract class Algorithm {
    
    protected Graph graph;
    protected boolean finished;

    // EFFECTS: creates a new algorithm with given graph
    public Algorithm(Graph graph) {
        this.graph = graph;
        finished = false;
    }
    
    // EFFECTS: returns the algorithm's graph 
    public Graph getGraph() {
        return graph;
    }

    // REQUIRES: algorithm must be started
    // MODIFIES: this
    // EFFECTS: advance the algorithms search on the graph
    public abstract void step() throws AlgoFinished, AlgoOutOfMoves;

    // EFFECTS: returns whether the algorithm has finished
    public boolean isFinished() {
        return finished;
    }
    
} 
