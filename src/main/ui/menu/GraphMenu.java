package ui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Graph;
import model.NodeType;
import model.Position;
import ui.Constants;
import ui.SwingGUI;

/**
 * Repersents the menu that is shown when running the visualization
 **/
public class GraphMenu extends JPanel {

    private static Map<NodeType, Color> TYPE_MAPPING = Map.of(
            NodeType.EMPTY, Color.WHITE,
            NodeType.CLOSED, Color.RED,
            NodeType.OPEN, Color.GREEN,
            NodeType.WALL, Color.BLACK,
            NodeType.PATH, Color.MAGENTA);

    private Graph graph;

    // includes margin
    private int cellWidth;

    private SwingGUI gui;

    // REQUIRES: graph must have getCols() == getRows()
    // EFFECTS: creates a new GraphPanel with the given graph
    public GraphMenu(Graph graph, SwingGUI gui) {
        this.gui = gui;
        setLayout(null);
        setGraph(graph);
        initComponents();
    }

    private void initComponents() {
        JButton button = new JButton("Back");
        button.addActionListener(_ -> {
            gui.showMenu(Menu.START);
        });
        button.setBounds(0, 50, 100, 30);
        add(button);
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
                g.fillRect(Constants.GRAPH_MENU_X_POS + (col * cellWidth),
                        Constants.GRAPH_MENU_Y_POS + (row * cellWidth),
                        cellWidth - Constants.GRAPH_CELL_MARGIN,
                        cellWidth - Constants.GRAPH_CELL_MARGIN);
            }
        }
    }

    // REQUIRES: graph must have getRows() == getCols()
    // MODIFIES: this
    // EFFECTS: sets the graph to the given graph and reajusts panel dimensions
    public void setGraph(Graph graph) {
        this.graph = graph;
        cellWidth = Constants.GRAPH_SIDE_WIDTH / graph.getCols();
        setSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
    }

    // simple getters and setters
    public Graph getGraph() {
        return graph;
    }

    public int getCellWidth() {
        return cellWidth;
    }
}
