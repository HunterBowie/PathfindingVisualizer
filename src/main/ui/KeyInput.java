package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.algo.AStar;

/**
 * Repersents the handling of key events
 **/
public class KeyInput extends KeyAdapter {

    private SwingGUI gui;

    // EFFECTS: creates a new KeyInput with the given GUI  
    public KeyInput(SwingGUI gui) {
        this.gui = gui;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                if (!gui.getAlgorithm().isFinished()) {
                    gui.setRunning(true);
                }
                break;

            case KeyEvent.VK_C:
                gui.getGraph().resetAllNodes();
                gui.setRunning(false);
                gui.setAlgorithm(new AStar(gui.getGraph()));
                break;

            default:
                break;
        }

    }

}
