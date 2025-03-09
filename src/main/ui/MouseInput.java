package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import exceptions.IllegalPosition;
import model.Position;

/**
 * Repersents the handling of mouse events
 **/
public class MouseInput extends MouseAdapter {

    // EFFECTS: creates a new MouseInput with the given GUI  
    public MouseInput(SwingGUI gui) {
    }

    // EFFECTS: gets the current graph position which the mouse is above
    // throws IllegalPosition if the mouse is not above a legal position
    private Position getMousePosition() throws IllegalPosition {
        return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
