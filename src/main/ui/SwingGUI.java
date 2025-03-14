package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.json.JSONObject;

import exceptions.AlgoFinished;
import exceptions.AlgoOutOfMoves;
import model.Graph;
import model.Position;
import model.algo.AStar;
import model.algo.Algorithm;
import model.algo.BreadthFirst;
import model.algo.DepthFirst;
import persistence.ParseJson;
import persistence.ReadWriteJson;
import ui.input.GraphKeyInput;
import ui.input.GraphMouseInput;
import ui.menu.CreateGraphMenu;
import ui.menu.EnterFileMenu;
import ui.menu.GraphMenu;
import ui.menu.Menu;
import ui.menu.StartMenu;

/**
 * Repersents the GUI for the algorithm visualization
 */
public class SwingGUI extends JFrame {

    private static final int UPDATE_DELAY_MS = 10;

    private GraphMenu graphMenu;
    private Algorithm algo;
    private boolean running = false;
    private CardLayout menus;
    private JPanel menuContainer;
    private Menu showingMenu;

    // EFFECTS: constructs a new SwingGUI
    public SwingGUI() {
        super("Pathfinding Visualizer");
        graphMenu = new GraphMenu(new Graph(40, 40,
                new Position(0, 0), new Position(18, 17)), this);
        algo = new AStar(graphMenu.getGraph());
        menus = new CardLayout();
        menuContainer = new JPanel(menus);
        menuContainer.add(new StartMenu(this), Menu.START.name());
        menuContainer.add(new EnterFileMenu(this), Menu.ENTER_FILE.name());
        menuContainer.add(new CreateGraphMenu(this), Menu.CREATE_GRAPH.name());
        menuContainer.add(graphMenu, Menu.GRAPH.name());
        showMenu(Menu.START);
        add(menuContainer, BorderLayout.CENTER);
        setUp();
        updateVisualization();
    }

    // MODIFIES: this
    // EFFECTS: sets the correct settings in JFrame
    private void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setResizable(false);
        addKeyListener(new GraphKeyInput(this));
        addMouseListener(new GraphMouseInput(this));
        setSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        centreOnScreen();
        setFocusable(true);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: shows the given menu 
    public void showMenu(Menu menu) {
        menus.show(menuContainer, menu.name());
        showingMenu = menu;
    }

    // MODIFIES: this
    // EFFECTS: updates the visualization when running
    private void updateVisualization() {
        Timer t = new Timer(UPDATE_DELAY_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isShowingGraph()) {
                    graphMenu.repaint();
                    if (running) {
                        try {
                            algo.step();
                        } catch (AlgoOutOfMoves e) {
                            running = false;
                        } catch (AlgoFinished e) {
                            running = false;
                        }
                    }
                }
            }
        });

        t.start();
    }

    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on screen
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    // getters and setters
    public Algorithm getAlgorithm() {
        return algo;
    }

    public void setAlgorithm(Algorithm algo) {
        this.algo = algo;
    }

    public Graph getGraph() {
        return graphMenu.getGraph();
    }

    public void setGraph(Graph graph) {
        graphMenu.setGraph(graph);
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public GraphMenu getGraphMenu() {
        return graphMenu;
    }

    public boolean isShowingGraph() {
        return Menu.GRAPH == showingMenu;
    }

    public void resetAlgorithm() {
        if (algo instanceof AStar) {
            algo = new AStar(getGraph());
        } else if (algo instanceof BreadthFirst) {
            algo = new BreadthFirst(getGraph());
        } else {
            algo = new DepthFirst(getGraph());
        }
    }

}
