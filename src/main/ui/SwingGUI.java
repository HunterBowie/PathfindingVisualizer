package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import exceptions.AlgoFinished;
import exceptions.AlgoOutOfMoves;
import model.Graph;
import model.algo.AStar;
import model.algo.Algorithm;
import model.algo.BreadthFirst;
import model.algo.DepthFirst;
import ui.menu.CreateGraphMenu;
import ui.menu.EnterFileMenu;
import ui.menu.GraphMenu;
import ui.menu.Menu;
import ui.menu.SaveGraphMenu;
import ui.menu.StartMenu;

/**
 * Repersents the GUI for the algorithm visualization
 */
public class SwingGUI extends JFrame {

    private GraphMenu graphMenu;
    private Algorithm algo;
    private boolean running = false;
    private CardLayout menus;
    private JPanel menuContainer;
    private Menu showingMenu;
    private Timer updateTimer;

    // EFFECTS: constructs a new SwingGUI
    public SwingGUI() {
        super("Pathfinding Visualizer");
        graphMenu = new GraphMenu(this);
        menus = new CardLayout();
        menuContainer = new JPanel(menus);
        menuContainer.add(new StartMenu(this), Menu.START.name());
        menuContainer.add(new EnterFileMenu(this), Menu.ENTER_FILE.name());
        menuContainer.add(new CreateGraphMenu(this), Menu.CREATE_GRAPH.name());
        menuContainer.add(new SaveGraphMenu(this), Menu.SAVE.name());
        menuContainer.add(graphMenu, Menu.GRAPH.name());
        showMenu(Menu.START);
        add(menuContainer, BorderLayout.CENTER);
        setUp();
        setUpdateDelay(Constants.NORMAL_DELAY);
    }

    // MODIFIES: this
    // EFFECTS: sets the correct settings in JFrame
    private void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setResizable(false);
        addMouseListener(new GraphMouseInput(this));
        setSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        centreOnScreen();
        setFocusable(true);
        setVisible(true);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isShowingGraph()) {
                    graphMenu.repaint();
                }
            }
        });
        timer.start();
    }

    // MODIFIES: this
    // EFFECTS: shows the given menu
    public void showMenu(Menu menu) {
        menus.show(menuContainer, menu.name());
        showingMenu = menu;
    }

    // MODIFIES: this
    // EFFECTS: creates a timer that updates the visualization
    private void createUpdateTimer(int delayMilliSeconds) {
        updateTimer = new Timer(delayMilliSeconds, new ActionListener() {
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
    }

    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on screen
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    // EFFECTS: returns whether the panel being shown is the graph panel
    public boolean isShowingGraph() {
        return Menu.GRAPH == showingMenu;
    }

    // MODIFIES: this
    // EFFECTS: resets the current algorithm's progress
    public void resetAlgorithm() {
        if (algo instanceof AStar) {
            algo = new AStar(getGraph());
        } else if (algo instanceof BreadthFirst) {
            algo = new BreadthFirst(getGraph());
        } else {
            algo = new DepthFirst(getGraph());
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the update delay for the graph visualization
    public void setUpdateDelay(int delayMilliSeconds) {
        if (updateTimer != null) {
            updateTimer.stop();
        }
        createUpdateTimer(delayMilliSeconds);
        updateTimer.start();

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

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public GraphMenu getGraphMenu() {
        return graphMenu;
    }


}
