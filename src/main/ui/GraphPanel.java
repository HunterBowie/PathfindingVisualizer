package ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import model.Graph;

/**
 * Repersents the panel on the screen the contains the graph
 **/
public class GraphPanel extends JPanel {
   

    // EFFECTS: creates a new GraphPanel with the given graph
    public GraphPanel(Graph graph) {
    }

    @Override
    protected void paintComponent(Graphics g) {
    }

    // MODIFIES: this
    // EFFECTS: sets the graph to the given graph and reajusts panel dimensions
    public void setGraph(Graph graph) {
    }

    // simple getters and setters
    public Graph getGraph() {
        return null;
    }
}
