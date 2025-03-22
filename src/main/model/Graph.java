package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Repersents the grid in the visualization.
 **/
public class Graph {

    private int rows;
    private int cols;
    private Position startPos;
    private Position endPos;

    private List<List<Node>> nodes;

    // REQUIRES: rows >= 1, cols >= 1, startPos and endPos must be within rows/cols
    // EFFECTS: create a graph with given rows, cols
    public Graph(int rows, int cols, Position startPos, Position endPos) {
        this.rows = rows;
        this.cols = cols;
        this.startPos = startPos;
        this.endPos = endPos;
        initNodes();

    }

    // MODIFIES: this
    // EFFECTS: creates nodes matrix with all EMPTY nodes
    private void initNodes() {
        nodes = new ArrayList<>();
        
        for (int j = 0; j < rows; j++) {
            List<Node> row = new ArrayList<>();
            for (int i = 0; i < cols; i++) {
                row.add(new Node(NodeType.EMPTY));
            }
            nodes.add(row);
        }
        
    }

    // REQUIRES: pos is within graph dimesions
    // MODIFIES: this
    // EFFECTS: sets the pos on the graph to the given node
    public void setNode(Position pos, Node node) {
        List<Node> row = nodes.get(pos.getRow());
        row.set(pos.getCol(), node);
    }

    // REQUIRES: pos is within graph dimesions
    // EFFECTS: gets the node at the given pos
    public Node getNode(Position pos) {
        return nodes.get(pos.getRow()).get(pos.getCol());
    }

    // MODIFIES: this
    // EFFECTS: resets all the nodes in the graph to a new empty node
    public void resetAllNodes() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                setNode(new Position(row, col), new Node(NodeType.EMPTY));
            }
        }
    }

    // EFFECTS: returns whether the given position is legal on the graph
    public boolean isLegalPosition(Position pos) {
        if (pos.getRow() < 0 || pos.getCol() < 0) {
            return false;
        }
        if (pos.getRow() >= getRows() || pos.getCol() >= getCols()) {
            return false;
        }
        return true;
    }

    
    // MODIFIES: this
    // EFFECTS: resets all the nodes in the graph to a new node with same type
    public void softResetAllNodes() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Position pos = new Position(row, col);
                NodeType type = getNode(pos).getNodeType();
                if (type != NodeType.WALL) {
                    type = NodeType.EMPTY;
                }
                
                setNode(pos, new Node(type));
            }
        }
    }
    
    // getters and setters
    public List<List<Node>> getAllNodes() {
        return nodes;
    }
    
    public Position getStartPos() {
        return startPos;
    }

    public Position getEndPos() {
        return endPos;
    }

    public void setStartPos(Position pos) {
        startPos = pos;
    }

    public void setEndPos(Position pos) {
        endPos = pos;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

}
