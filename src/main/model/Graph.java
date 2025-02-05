package model;

public class Graph {

    // REQUIRES: rows >= 1, cols >= 1
    // EFFECTS: create a graph with given rows, cols
    public Graph(int rows, int cols) {
    }

    // REQUIRES: pos is within graph dimesions
    // MODIFIES: this
    // EFFECTS: sets the pos on the graph to the given nodeType
    public void setNode(Position pos, NodeType nodeType) {
    }

    // REQUIRES: pos is within graph dimesions
    // EFFECTS: gets the nodeType at the given pos
    public NodeType getNode(Position pos) {
        return null;
    }

    public Position getStartPos() {
        return null;
    }

    public Position getEndPos() {
        return null;
    }

    public void setStartPos(Position pos) {
    }

    public void setEndPos(Position pos) {
    }

}
