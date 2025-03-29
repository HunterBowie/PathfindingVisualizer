package ui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.EventLog;
import model.Graph;
import model.NodeType;
import model.Position;
import model.algo.AStar;
import model.algo.BreadthFirst;
import model.algo.DepthFirst;
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

    private static String[] ALGO_NAMES = { "AStar", "Breadth First", "Depth First" };
    private static String[] SPEEDS = { "Hyper", "Fast", "Normal", "Slow" };

    private Graph graph;

    // includes margin
    private int cellWidth;

    private SwingGUI gui;
    
    // EFFECTS: creates a new GraphPanel with the given graph
    public GraphMenu(SwingGUI gui) {
        this.gui = gui;
        setLayout(null);
        initComponents();
    }

    // EFFECTS: prints the log to the console
    private void printLog() {
        for (model.Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds all the components on the left side to the menu
    private void initLeftSideComponents() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            gui.showMenu(Menu.START);
        });
        backButton.setBounds(0, 0, 100, 30);
        add(backButton);

        JButton quiteButton = new JButton("Quit");
        quiteButton.addActionListener(e -> {
            printLog();
            System.exit(0);
        });
        quiteButton.setBounds(0, 470, 100, 30);
        add(quiteButton);

        JComboBox<String> algoComboBox = new JComboBox<>(ALGO_NAMES);
        algoComboBox.addActionListener(e -> onChangeAlgo(algoComboBox));
        algoComboBox.setBounds(0, 150, 100, 30);
        add(algoComboBox);

        JComboBox<String> speedComboBox = new JComboBox<>(SPEEDS);
        speedComboBox.setSelectedItem("Normal");
        speedComboBox.addActionListener(e -> onSpeedChange(speedComboBox));
        speedComboBox.setBounds(0, 250, 100, 30);
        add(speedComboBox);
    }

    // MODIFIES: this
    // EFFECTS: adds all the components on the right side to the menu
    private void initRightSideComponents() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> gui.showMenu(Menu.SAVE));
        saveButton.setBounds(600, 470, 100, 30);
        add(saveButton);

        JButton runButton = new JButton("Run");
        runButton.addActionListener(e -> {
            if (!gui.getAlgorithm().isFinished()) {
                gui.setRunning(true);
            }
        });
        runButton.setBounds(600, 150, 100, 30);
        add(runButton);

        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> onPause());
        pauseButton.setBounds(600, 200, 100, 30);
        add(pauseButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clear());
        clearButton.setBounds(600, 250, 100, 30);
        add(clearButton);
    }

    // MODIFIES: this
    // EFFECTS: adds all the components to the menu
    private void initComponents() {
        initLeftSideComponents();
        initRightSideComponents();
    }

    // MODIFIES: this
    // EFFECTS: pauses the GUI algorithm
    private void onPause() {
        if (!gui.getAlgorithm().isFinished()) {
            gui.setRunning(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: clears the GUI graph nodes
    private void clear() {
        gui.getGraph().resetAllNodes();
        gui.setRunning(false);
        gui.resetAlgorithm();
    }

    // MODIFIES: this
    // EFFECTS: changes the GUI algorithm 
    private void onChangeAlgo(JComboBox<String> comboBox) {
        gui.getGraph().softResetAllNodes();
        switch ((String) comboBox.getSelectedItem()) {
            case "AStar":
                gui.setAlgorithm(new AStar(gui.getGraph()));
                break;

            case "Breadth First":
                gui.setAlgorithm(new BreadthFirst(gui.getGraph()));
                break;

            case "Depth First":
                gui.setAlgorithm(new DepthFirst(gui.getGraph()));
                break;

            default:
                throw new RuntimeException("This shouldn't be possible");
        }
        gui.requestFocusInWindow();
    }

    // MODIFIES: this
    // EFFECTS: changes the GUI algorithm speed 
    private void onSpeedChange(JComboBox<String> comboBox) {
        switch ((String) comboBox.getSelectedItem()) {
            case "Hyper":
                gui.setUpdateDelay(Constants.HYPER_DELAY);
                break;

            case "Fast":
                gui.setUpdateDelay(Constants.FAST_DELAY);
                break;

            case "Normal":
                gui.setUpdateDelay(Constants.NORMAL_DELAY);
                break;

            case "Slow":
                gui.setUpdateDelay(Constants.SLOW_DELAY);
                break;

            default:
                throw new RuntimeException("This shouldn't be possible");
        }
        gui.requestFocusInWindow();
    }

    // MODIFIES: g
    // EFFECTS: paints the graph on in the center of the screen
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
                g.fillRect(Constants.GRAPH_X_POS + (col * cellWidth),
                        Constants.GRAPH_Y_POS + (row * cellWidth),
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
