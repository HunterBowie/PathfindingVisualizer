package ui;

import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import exceptions.IllegalMousePosition;
import model.NodeType;
import model.Position;

/**
 * Repersents the handling of mouse events
 **/
public class GraphMouseInput extends MouseAdapter {

    private SwingGUI gui;
    private Timer whileMouseHeld;
    private int mouseButtonHeld;
    private boolean mouseHeld = false;

    // EFFECTS: creates a new MouseInput with the given GUI
    public GraphMouseInput(SwingGUI gui) {
        this.gui = gui;
        whileMouseHeld = new Timer(Constants.MOUSE_HELD_DELAY_MS, _ -> {
            if (gui.isRunning()) {
                return;
            }
            NodeType nodeType = NodeType.WALL;
            if (mouseButtonHeld == MouseEvent.BUTTON3) {
                nodeType = NodeType.EMPTY;
            }
            try {
                Position pos = getMousePosition();
                gui.getGraph().getNode(pos).setNodeType(nodeType);
            } catch (IllegalMousePosition e) {
                // pass
            }
        });
    }

    // EFFECTS: gets the current graph position which the mouse is above
    // throws IllegalPosition if the mouse is not above a legal position
    private Position getMousePosition() throws IllegalMousePosition {
        Point mousePoint = getMousePoint();
        if (mousePoint.x <= Constants.GRAPH_X_POS || mousePoint.y <= Constants.GRAPH_Y_POS) {
            throw new IllegalMousePosition();
        }
        int col = (mousePoint.x - Constants.GRAPH_X_POS) / gui.getGraphMenu().getCellWidth();
        int row = (mousePoint.y - Constants.GRAPH_Y_POS)
                / gui.getGraphMenu().getCellWidth();
        Position pos = new Position(row, col);
        if (!gui.getGraph().isLegalPosition(pos)) {
            throw new IllegalMousePosition();
        }
        return pos;
    }

    // EFFECTS: gets current mouse pos relative to the top left of the inner window
    private Point getMousePoint() {
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        Point framePoint = gui.getLocationOnScreen();
        Insets insets = gui.getInsets();

        mousePoint.x -= framePoint.x;

        mousePoint.y -= insets.top;
        mousePoint.y -= framePoint.y;

        return mousePoint;
    }

    // MODIFIES: this
    // EFFECTS: triggers the mouse held timer
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.gui.isShowingGraph()) {
            if (!mouseHeld) {
                mouseHeld = true;
                mouseButtonHeld = e.getButton();
                whileMouseHeld.start();
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: cancels the mouse held timer
    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.gui.isShowingGraph()) {
            mouseHeld = false;
            whileMouseHeld.stop();
        }
    }

}
