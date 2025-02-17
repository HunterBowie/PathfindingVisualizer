package model;

/**
 * Repersents a the type of a node
 **/
public class Node {

    private double costG;
    private double costH;
    private NodeType nodeType;

    
    // EFFECTS: creates a new Node
    public Node(NodeType nodeType) {
        this.nodeType = nodeType;
        this.costG = Double.POSITIVE_INFINITY;
        this.costH = -1;
    }

    // EFFECTS: return if the given node has a lower f cost than the this one
    public boolean isLowerCost(Node node) {
        if (node.getFCost() < this.getFCost()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (nodeType == NodeType.EMPTY) {
            return "-";
        }
        if (nodeType == NodeType.CLOSED) {
            return "0";
        }
        if (nodeType == NodeType.PATH) {
            return "P";
        }
        if (nodeType == NodeType.OPEN) {
            return "O";
        }
        return "X";
    }
    
    // EFFECTS: returns the gcost + hcost
    public double getFCost() {
        return getGCost() + getHCost();
    }

    // getters and setters
    public double getGCost() {
        return costG;
    }

    public double getHCost() {
        return costH;
    }

    public void setGCost(double cost) {
        costG = cost;
    }

    public void setHCost(double cost) {
        costH = cost;
    }


    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }
    
}
