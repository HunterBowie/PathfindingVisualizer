package model;

/**
 * Repersents a the type of a node
 **/
public class Node {
    
    // EFFECTS: creates a new Node
    public Node(NodeType nodeType) {
    }

    // EFFECTS: return if the given node has a lower f cost than the this one
    public boolean isLowerCost(Node node) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    // getters and setters
    public double getGCost() {
        return 0;
    }

    public double getHCost() {
        return 0;
    }

    public void setGCost() {
    }

    public void setHCost() {
    }

    public double getFCost() {
        return 0;
    }
    
}
