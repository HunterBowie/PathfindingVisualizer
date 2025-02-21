package model.algo;

import java.util.ArrayList;
import java.util.List;

import exceptions.AlgoFinished;
import exceptions.AlgoOutOfMoves;
import model.Graph;
import model.NodeType;
import model.Position;

/**
 * Repersents a Depth or Breadth First pathfinding algorithm
 **/
public class DepthBreadth extends Algorithm {

    private List<Position> stack;
    private List<List<Position>> paths;
    private boolean isDepth;

    // EFFECTS: creates a Depth or Breadth First algorithm with a given graph
    public DepthBreadth(Graph graph, boolean isDepth) {
        super(graph);
        this.isDepth = isDepth;
        stack = new ArrayList<>();
        stack.add(graph.getStartPos());
        paths = new ArrayList<>();
        paths.add(new ArrayList<>()); // path to start is empty
    }

    @Override
    protected void highlightPath() {
        int index = 0;
        if (isDepth) {
            index = stack.size() - 1;
        }
        List<Position> path = paths.get(index);

        for (Position pos : path) {
            graph.getNode(pos).setNodeType(NodeType.PATH);
        }

    }

    // EFFECTS: returns a list of immediate neighbors that can be searched
    private List<Position> getNeighbors(Position current, List<Position> path) {
        List<Position> positions = current.getNeighbors();
        List<Position> filteredPositions = new ArrayList<>();

        for (Position neighborPos : positions) {
            // not valid positions
            if (neighborPos.getCol() < 0 || neighborPos.getCol() >= graph.getCols()) {
                continue;
            }
            if (neighborPos.getRow() < 0 || neighborPos.getRow() >= graph.getRows()) {
                continue;
            }
            if (graph.getNode(neighborPos).getNodeType() == NodeType.WALL) {
                continue;
            }
            if (path.contains(neighborPos)) {
                continue;
            }
            filteredPositions.add(neighborPos);
        }
        return filteredPositions;
    }

    // REQUIRES: 0 >= index < stack.size()
    // MODIFIES: this
    // EFFECTS: checks the neighbors of pos at index and adds them to the stack
    private void processCurrentPos(int index) { 
        Position currentPosition = stack.get(index);

        List<Position> path = paths.get(index);

        List<Position> neighborPositions = getNeighbors(currentPosition, path);

        List<Position> newPath = new ArrayList<>(path);
        newPath.add(currentPosition);

        stack.remove(index);
        paths.remove(index);

        stack.addAll(neighborPositions);

        for (int i = 0; i < neighborPositions.size(); i++) {
            paths.add(new ArrayList<>(newPath));
        }

        for (Position pos : stack) {
            graph.getNode(pos).setNodeType(NodeType.OPEN);
        }
        for (Position pos : newPath) {
            graph.getNode(pos).setNodeType(NodeType.CLOSED);
        }
    }

    @Override
    public void step() throws AlgoFinished, AlgoOutOfMoves {
        if (stack.isEmpty()) {
            throw new AlgoOutOfMoves();
        }
        if (finished) {
            throw new AlgoFinished();
        }
        
        int index = 0;

        if (isDepth) {
            index = stack.size() - 1;
        }
        
        Position currentPosition = stack.get(index);

        List<Position> path = paths.get(index);

        if (currentPosition.equals(graph.getEndPos())) {
            highlightPath();
            finished = true;
            return;
        }
        processCurrentPos(index);
    }

}
