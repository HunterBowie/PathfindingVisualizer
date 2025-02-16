package model;

public interface Algorithm {

    // MODIFIES: this
    // EFFECTS: advance the algorithms search on the graph
    public void step();

    public boolean isFinished();
    
} 
