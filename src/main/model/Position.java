package model;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Position pos = (Position) obj;
        return row == pos.row && col == pos.col;
    }


    // EFFECTS: returns the distance between the two positions
    public static double getDistance(Position pos1, Position pos2) {
        int xDiff = pos1.getCol() - pos2.getCol();
        int yDiff = pos1.getRow() - pos2.getRow();
        return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
    }

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

}
