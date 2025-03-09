package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import exceptions.AlgoFinished;
import exceptions.AlgoOutOfMoves;
import model.Graph;
import model.Position;
import model.algo.AStar;
import model.algo.Algorithm;

/**
 * Repersents the GUI for the algorithm visualization
 */
public class SwingGUI extends JFrame {

    private static final int UPDATE_DELAY_MS = 10;

    private GraphPanel graphPanel;
    private Algorithm algo;
    private boolean running = false;

    // EFFECTS: constructs a new SwingGUI
    public SwingGUI() {
        super("Pathfinding Visualization");
        graphPanel = new GraphPanel(new Graph(30, 30,
                new Position(5, 5), new Position(20, 20)));
        algo = new AStar(graphPanel.getGraph());
        add(graphPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setVisible(true);
        pack();
        centreOnScreen();
        start();
        setResizable(false);
        addKeyListener(new KeyInput(this));
        addMouseListener(new MouseInput(this));
    }

    // MODIFIES: this
    // EFFECTS: initializes a timer that updates the visualization
    private void start() {
        Timer t = new Timer(UPDATE_DELAY_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                graphPanel.repaint();
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
        });

        t.start();
    }

    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on screen
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // getters and setters
    public Algorithm getAlgorithm() {
        return algo;
    }

    public void setAlgorithm(Algorithm algo) {
        this.algo = algo;
    }

    public Graph getGraph() {
        return graphPanel.getGraph();
    }

    public void setGraph(Graph graph) {
        graphPanel.setGraph(graph);
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
