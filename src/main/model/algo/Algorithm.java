package model.algo;

import exceptions.AlgoFinished;
import exceptions.AlgoOutOfMoves;
import model.Graph;

/**
 * Repersents a type of pathfinding algorithm
 **/
public abstract class Algorithm {
    

    // MODIFIES: this
    // EFFECTS: sets this algorithms graph
    public void setGraph(Graph graph) {
    }
    
    // EFFECTS: returns the algorithm's graph 
    public Graph getGraph(Graph graph) {
        return null;
    }

    // REQUIRES: graph must be set
    // MODIFIES: this
    // EFFECTS: sets up the algorithm with the graph
    public abstract void start();

    // REQUIRES: algorithm must be started
    // MODIFIES: this
    // EFFECTS: advance the algorithms search on the graph
    public abstract void step() throws AlgoFinished, AlgoOutOfMoves;

    // EFFECTS: returns whether the algorithm has finished
    public boolean isFinished() {
        return false;
    }
    
} 
