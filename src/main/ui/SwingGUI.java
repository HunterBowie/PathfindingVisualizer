package ui;

import javax.swing.JFrame;
import model.Graph;
import model.algo.Algorithm;

/**
 * Repersents the GUI for the algorithm visualization
 */
public class SwingGUI extends JFrame {

    // EFFECTS: constructs a new SwingGUI
    public SwingGUI() {
    }
    
    // MODIFIES: this
    // EFFECTS: initializes a timer that updates the visualization
    private void start() {
    }

    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on screen
    private void centreOnScreen() {
    }

    // getters and setters
    public Algorithm getAlgorithm() {
        return null;
    }

    public void setAlgorithm(Algorithm algo) {
    }

    public Graph getGraph() {
        return null;
    }

    public void setGraph(Graph graph) {
    }

    public boolean getRunning() {
        return false;
    }

    public void setRunning(boolean running) {
    }

}
