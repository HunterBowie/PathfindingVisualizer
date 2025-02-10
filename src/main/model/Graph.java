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

    private List<List<NodeType>> nodes;

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
        List<NodeType> row = new ArrayList<>();
        for (int j = 0; j < cols; j++) {
            row.add(NodeType.EMPTY);
        }
        for (int i = 0; i < rows; i++) {
            nodes.add(new ArrayList<>(row));
        }
    }

    // REQUIRES: pos is within graph dimesions
    // MODIFIES: this
    // EFFECTS: sets the pos on the graph to the given nodeType
    public void setNode(Position pos, NodeType nodeType) {
        List<NodeType> row = nodes.get(pos.getRow());
        row.set(pos.getCol(), nodeType);
    }

    // REQUIRES: pos is within graph dimesions
    // EFFECTS: gets the nodeType at the given pos
    public NodeType getNode(Position pos) {
        return nodes.get(pos.getRow()).get(pos.getCol());
    }

    // MODIFIES: this
    // EFFECTS: sets all the nodes in the graph to the given nodeType
    public void setAllNodes(NodeType nodeType) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                nodes.get(row).set(col, nodeType);
            }
        }
    }

    // getters and setters
    public List<List<NodeType>> getAllNodes() {
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
