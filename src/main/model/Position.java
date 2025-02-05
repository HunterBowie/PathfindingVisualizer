package model;

/**
 * Repersents a position on a graph.
 **/
public class Position {

    int row;
    int col;

    // REQUIRES: row >= 0, col >= 0
    // EFFECTS: creates a new Position with given row, col
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
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
