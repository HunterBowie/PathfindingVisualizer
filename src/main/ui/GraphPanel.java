package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.JPanel;

import model.Graph;
import model.NodeType;
import model.Position;

/**
 * Repersents the panel on the screen the contains the graph
 **/
public class GraphPanel extends JPanel {
    // the total cell width (including margin)
    public static final int CELL_WIDTH = 20;
    // the margin between cells
    public static final int CELL_MARGIN = 2;

    private static Map<NodeType, Color> TYPE_MAPPING = Map.of(
            NodeType.EMPTY, Color.WHITE,
            NodeType.CLOSED, Color.RED,
            NodeType.OPEN, Color.GREEN,
            NodeType.WALL, Color.BLACK,
            NodeType.PATH, Color.MAGENTA);

    private Graph graph;

    // EFFECTS: creates a new GraphPanel with the given graph
    public GraphPanel(Graph graph) {
        setGraph(graph);
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < graph.getRows(); row++) {
            for (int col = 0; col < graph.getCols(); col++) {
                Position pos = new Position(row, col);

                if (pos.equals(graph.getStartPos())) {
                    g.setColor(Color.CYAN);
                } else if (pos.equals(graph.getEndPos())) {
                    g.setColor(Color.ORANGE);
                } else {
                    NodeType type = graph.getNode(pos).getNodeType();
                    Color color = TYPE_MAPPING.get(type);
                    g.setColor(color);
                }
                g.fillRect(col * CELL_WIDTH, row * CELL_WIDTH,
                        CELL_WIDTH - CELL_MARGIN, CELL_WIDTH - CELL_MARGIN);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the graph to the given graph and reajusts panel dimensions
    public void setGraph(Graph graph) {
        this.graph = graph;
        setPreferredSize(new Dimension(graph.getCols() * CELL_WIDTH,
                graph.getRows() * CELL_WIDTH));
    }

    // simple getters and setters
    public Graph getGraph() {
        return graph;
    }
}
