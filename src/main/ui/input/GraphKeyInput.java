package ui.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ui.SwingGUI;


/**
 * Repersents the handling of key events
 **/
public class GraphKeyInput extends KeyAdapter {

    private SwingGUI gui;

    // EFFECTS: creates a new KeyInput with the given GUI
    public GraphKeyInput(SwingGUI gui) {
        this.gui = gui;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.gui.isShowingGraph()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_R:
                    if (!gui.getAlgorithm().isFinished()) {
                        gui.setRunning(true);
                    }
                    break;

                case KeyEvent.VK_C:
                    gui.getGraph().resetAllNodes();
                    gui.setRunning(false);
                    gui.resetAlgorithm();
                    break;

                default:
                    break;
            }

        }
    }

}
