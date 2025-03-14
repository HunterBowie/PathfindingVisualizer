package ui.menu;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.io.UncheckedIOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Graph;
import model.Position;
import model.algo.AStar;
import model.algo.BreadthFirst;
import model.algo.DepthFirst;
import ui.SwingGUI;

/**
 * Repersents the menu for creating a new graph
 **/
public class CreateGraphMenu extends JPanel {

    private SwingGUI gui;

    // EFFECTS: creates a new CreateGraphMenu with given gui
    public CreateGraphMenu(SwingGUI gui) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
        this.gui = gui;
    }

    // MODIFIES: this
    // EFFECTS: adds all the components to the menu
    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createVerticalGlue());
        JLabel title = new JLabel("Enter number of columns/rows (one number) and Algorithm");
        add(title);
        JTextField textbox = new JTextField();
        textbox.setMaximumSize(new Dimension(150, 50));
        add(textbox);
        
        String[] options = { "AStar", "Breadth First", "Depth First" };
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setMaximumSize(new Dimension(150, 30));
        add(comboBox);
        
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(_ -> onSubmit(textbox, comboBox));
        textbox.addActionListener(_ -> onSubmit(textbox, comboBox));
        add(submitButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(_ -> gui.showMenu(Menu.START));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(backButton);
        this.add(Box.createVerticalGlue());
    }

    // MODIFIES: this
    // EFFECTS: attempts to load the graph from the values in the textbox and combobox
    private void onSubmit(JTextField textbox, JComboBox<String> comboBox) {
        try {
            int rows = Integer.valueOf(textbox.getText().strip());
            gui.getGraphMenu().setGraph(new Graph(rows, rows,
                    new Position(0, 0), new Position(rows - 1, rows - 1)));

            switch ((String) comboBox.getSelectedItem()) {
                case "AStar":
                    gui.setAlgorithm(new AStar(gui.getGraphMenu().getGraph()));
                    break;
                case "Breadth First":
                    gui.setAlgorithm(new BreadthFirst(gui.getGraphMenu().getGraph()));
                    break;
                case "Depth First":
                    gui.setAlgorithm(new DepthFirst(gui.getGraphMenu().getGraph()));
                    break;

                default:
                    System.out.println("BAD STUFF");
                    break;
            }

            gui.showMenu(Menu.GRAPH);

        } catch (NumberFormatException e) {
            // pass
        } finally {
            textbox.setText("");
        }

    }

    @Override
    public Component add(Component component) {
        ((JComponent) component).setAlignmentX(Component.CENTER_ALIGNMENT);
        return super.add(component);
    }

}
