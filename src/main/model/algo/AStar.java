package model.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exceptions.AlgoFinished;
import exceptions.AlgoOutOfMoves;
import model.Graph;
import model.Node;
import model.NodeType;
import model.Position;

/**
 * Repersents an A* pathfinding algorithm
 **/
public class AStar extends Algorithm {

    private List<Position> open;
    private HashMap<String, String> pathHistory;

    // EFFECTS: create a new AStar with the given graph
    public AStar(Graph graph) {
        super(graph);
        this.open = new ArrayList<>();
        this.pathHistory = new HashMap<>();
        graph.getNode(graph.getStartPos()).setGCost(0);
        graph.getNode(graph.getStartPos()).setHCost(calcHCost(graph.getStartPos()));
        this.open.add(graph.getStartPos());
    }

    // EFFECTS: returns the hcost for a given position
    private double calcHCost(Position pos) {
        return Position.getDistance(graph.getEndPos(), pos);
    }

    // EFFECTS: returns a list of immediate neighbors that can be searched
    private List<Position> getNeighbors(Position centerPos) {
        List<Position> positions = centerPos.getNeighbors();
        List<Position> filteredPositions = new ArrayList<>();

        for (Position pos : positions) {
            // not valid positions
            if (pos.getCol() < 0 || pos.getCol() >= graph.getCols()) {
                continue;
            }
            if (pos.getRow() < 0 || pos.getRow() >= graph.getRows()) {
                continue;
            }
            if (graph.getNode(pos).getNodeType() == NodeType.WALL) {
                continue;
            }
            if (graph.getNode(pos).getNodeType() == NodeType.CLOSED) {
                continue;
            }
            filteredPositions.add(pos);
        }
        return filteredPositions;
    }

    // EFFECTS: return the open position that holds the node with the lowest f cost
    private Position findLowestFCostOpen() {
        double minCost = -1;
        Position minPos = null;
        for (Position pos : open) {
            Node node = graph.getNode(pos);
            if (minPos == null) {
                minPos = pos;
                minCost = node.getFCost();
                continue;
            }
            if (node.getFCost() < minCost) {
                minCost = node.getFCost();
                minPos = pos;
            }
        }
        return minPos;
    }

    // REQUIRES: end pos must be in cameFrom
    // MODIFIES: this
    // EFFECTS: highlights the shortest path on the graph
    private void highlightPath() {
        Position next = Position.load(pathHistory.get(graph.getEndPos().toString()));

        while (pathHistory.containsKey(next.toString())) {
            graph.getNode(next).setNodeType(NodeType.PATH);
            next = Position.load(pathHistory.get(next.toString()));
        }

    }

    // REQUIRES: currentPos must be a position within the bonds of graph
    // MODIFIES: this
    // EFFECTS: checks the neighbors of currentPos and updates their cost values
    private void processCurrentPos(Position currentPos) {
        open.remove(currentPos);
        graph.getNode(currentPos).setNodeType(NodeType.CLOSED);

        Node currentNode = graph.getNode(currentPos);

        for (Position neighbor : getNeighbors(currentPos)) {
            Node neighborNode = graph.getNode(neighbor);
            double newGCost = currentNode.getGCost() + Position.getDistance(currentPos, neighbor);
            if (newGCost < neighborNode.getGCost()) {
                pathHistory.put(neighbor.toString(), currentPos.toString());
                neighborNode.setGCost(newGCost);
                if (neighborNode.getHCost() == -1) {
                    neighborNode.setHCost(Position.getDistance(neighbor, graph.getEndPos()));
                }
                else {
                    System.out.println("sla;fdk;jss");
                }
                if (!open.contains(neighbor)) {
                    open.add(neighbor);
                    neighborNode.setNodeType(NodeType.OPEN);
                }
            }
        }
    }

    @Override
    public void step() {
        if (finished) {
            throw new AlgoFinished();
        }
        if (open.isEmpty()) {
            throw new AlgoOutOfMoves();
        }

        Position currentPos = findLowestFCostOpen();

        if (currentPos.equals(graph.getEndPos())) {
            finished = true;
            highlightPath();
            return;
        }

        processCurrentPos(currentPos);
    }

}
