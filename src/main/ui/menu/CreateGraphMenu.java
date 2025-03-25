package ui.menu;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Graph;
import model.Position;
import model.algo.AStar;
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

        add(Box.createVerticalGlue());
        JLabel title = new JLabel("Enter graph size (rows/columns), start position, and end position");
        add(title);
        JTextField sizeTextBox = new JTextField("10");
        sizeTextBox.setMaximumSize(new Dimension(150, 50));
        add(sizeTextBox);

        JTextField startTextBox = new JTextField("1, 4");
        startTextBox.setMaximumSize(new Dimension(150, 50));
        add(startTextBox);

        JTextField endTextBox = new JTextField("3, 2");
        endTextBox.setMaximumSize(new Dimension(150, 50));
        add(endTextBox);

        JButton submitButton = new JButton("Submit");
        sizeTextBox.addActionListener(_ -> onSubmit(sizeTextBox, startTextBox, endTextBox));
        submitButton.addActionListener(_ -> onSubmit(sizeTextBox, startTextBox, endTextBox));
        add(submitButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(_ -> gui.showMenu(Menu.START));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(backButton);
        add(Box.createVerticalGlue());
    }

    // MODIFIES: this
    // EFFECTS: attempts to load the graph from the values in the textbox and
    // combobox
    private void onSubmit(JTextField sizeTextBox, JTextField startTextBox, JTextField endTextBox) {
        try {
            int rows = Integer.valueOf(sizeTextBox.getText().strip());
            String[] rawStartPos = startTextBox.getText().replace(" ", "").split(",");
            int startRow = Integer.valueOf(rawStartPos[0]);
            int startCol = Integer.valueOf(rawStartPos[1]);
            String[] rawEndPos = endTextBox.getText().replace(" ", "").split(",");
            int endRow = Integer.valueOf(rawEndPos[0]);
            int endCol = Integer.valueOf(rawEndPos[1]);
            if (endRow < rows && endCol < rows && startRow < rows && startCol < rows) {
                gui.setGraph(new Graph(rows, rows,
                        new Position(startRow, startCol), new Position(endRow, endCol)));

                gui.setAlgorithm(new AStar(gui.getGraphMenu().getGraph()));

                gui.showMenu(Menu.GRAPH);
            }
           

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // pass
        } finally {
            sizeTextBox.setText("");
            startTextBox.setText("");
            endTextBox.setText("");
        }

    }

    // MODIFIES: this, component
    // EFFECTS: adds component to panel while seting its alignment to center
    @Override
    public Component add(Component component) {
        ((JComponent) component).setAlignmentX(Component.CENTER_ALIGNMENT);
        return super.add(component);
    }

}
