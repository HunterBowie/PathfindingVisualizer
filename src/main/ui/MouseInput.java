package ui;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import exceptions.IllegalPosition;
import model.NodeType;
import model.Position;

/**
 * Repersents the handling of mouse events
 **/
public class MouseInput extends MouseAdapter {

    public static final int TOP_BAR_WIDTH = 30;
    public static final int MOUSE_HELD_DELAY_MS = 2;

    private SwingGUI gui;
    private Timer whileMouseHeld;
    private int mouseButtonHeld;
    private boolean mouseHeld = false;

    // EFFECTS: creates a new MouseInput with the given GUI  
    public MouseInput(SwingGUI gui) {
        this.gui = gui;
        whileMouseHeld = new Timer(MOUSE_HELD_DELAY_MS, _ -> {
            NodeType nodeType = NodeType.WALL;
            if (mouseButtonHeld == MouseEvent.BUTTON3) {
                nodeType = NodeType.EMPTY;
            }
            try {
                Position pos = getMousePosition();
                gui.getGraph().getNode(pos).setNodeType(nodeType);
            } catch (IllegalPosition e) {
                // pass
            }
        });
    }

    // EFFECTS: gets the current graph position which the mouse is above
    // throws IllegalPosition if the mouse is not above a legal position
    private Position getMousePosition() throws IllegalPosition {
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePoint, gui);
        int col = (mousePoint.x) / GraphPanel.CELL_WIDTH;
        int row = (mousePoint.y - TOP_BAR_WIDTH) / GraphPanel.CELL_WIDTH;
        Position pos = new Position(row, col);
        if (!gui.getGraph().isLegalPosition(pos)) {
            throw new IllegalPosition();
        }
        return pos;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!mouseHeld) {
            mouseHeld = true;
            mouseButtonHeld = e.getButton();
            whileMouseHeld.start();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseHeld = false;
        whileMouseHeld.stop();
    }

}
