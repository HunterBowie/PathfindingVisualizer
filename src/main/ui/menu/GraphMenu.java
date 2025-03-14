package ui.menu;

import java.awt.Graphics;

import javax.swing.JPanel;

import model.Graph;
import ui.SwingGUI;

/**
 * Repersents the menu that is shown when running the visualization
 **/
public class GraphMenu extends JPanel {

   
    // REQUIRES: graph must have getCols() == getRows()
    // EFFECTS: creates a new GraphPanel with the given graph
    public GraphMenu(Graph graph, SwingGUI gui) {
    }

    private void initComponents() {
    }

    @Override
    protected void paintComponent(Graphics g) {
    }

    // REQUIRES: graph must have getRows() == getCols()
    // MODIFIES: this
    // EFFECTS: sets the graph to the given graph and reajusts panel dimensions
    public void setGraph(Graph graph) {
    }

    // simple getters and setters
    public Graph getGraph() {
        return null;
    }

    public int getCellWidth() {
        return 0;
    }
}
