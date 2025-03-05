package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Repersents a position on a graph.
 **/
public class Position {

    private int row;
    private int col;

    // REQUIRES: row >= 0, col >= 0
    // EFFECTS: creates a new Position with given row, col
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // EFFECTS: returns the distance between the two positions
    public static double getDistance(Position pos1, Position pos2) {
        int diffX = pos1.getCol() - pos2.getCol();
        int diffY = pos1.getRow() - pos2.getRow();
        return Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + col;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (row != other.row)
            return false;
        if (col != other.col)
            return false;
        return true;
    }

    // getters and setters
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    // EFFECTS: return the list of immediate neighbors
    public List<Position> getNeighbors() {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(row + 1, col));
        positions.add(new Position(row, col + 1));
        positions.add(new Position(row + 1, col + 1));
        positions.add(new Position(row - 1, col));
        positions.add(new Position(row, col - 1));
        positions.add(new Position(row - 1, col - 1));
        positions.add(new Position(row - 1, col + 1));
        positions.add(new Position(row + 1, col - 1));
        return positions;
    }

    @Override
    public String toString() {
        return Integer.toString(row) + "," + Integer.toString(col);
    }

    public static Position load(String encoded) {
        String[] bits = encoded.split(",");
        int row = Integer.valueOf(bits[0]);
        int col = Integer.valueOf(bits[1]);
        return new Position(row, col);
    }

}
