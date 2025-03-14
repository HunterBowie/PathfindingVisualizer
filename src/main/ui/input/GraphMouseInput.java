package ui.input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import exceptions.IllegalPosition;
import model.NodeType;
import model.Position;
import ui.Constants;
import ui.SwingGUI;

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
        int col = (mousePoint.x - Constants.GRAPH_MENU_X_POS) / gui.getGraphMenu().getCellWidth();
        int row = (mousePoint.y - Constants.TOP_BAR_WIDTH - Constants.GRAPH_MENU_Y_POS)
                / gui.getGraphMenu().getCellWidth();
        Position pos = new Position(row, col);
        if (!gui.getGraph().isLegalPosition(pos)) {
            throw new IllegalPosition();
        }
        return pos;
    }

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

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.gui.isShowingGraph()) {
            mouseHeld = false;
            whileMouseHeld.stop();
        }
    }

}
