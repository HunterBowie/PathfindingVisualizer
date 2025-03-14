package ui.menu;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.io.UncheckedIOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONObject;

import model.Graph;
import persistence.ParseJson;
import persistence.ReadWriteJson;
import ui.SwingGUI;


/**
 * Repersents the menu for entering a file to load from
 **/
public class EnterFileMenu extends JPanel {
    
    private SwingGUI gui;

    // EFFECTS: creates a new EnterFileMenu with given gui
    public EnterFileMenu(SwingGUI gui) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
        this.gui = gui;
    }

    // MODIFIES: this
    // EFFECTS: adds all the components to the menu
    private void initComponents() {
        add(Box.createVerticalGlue());
        JLabel title = new JLabel("Enter the name of the JSON file (with extension)");
        add(title);
        JTextField textbox = new JTextField();
        textbox.setMaximumSize(new Dimension(150, 50));
        textbox.addActionListener(_ -> onSubmit(textbox));
        add(textbox);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(_ -> onSubmit(textbox));
        add(submitButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(_ -> gui.showMenu(Menu.START));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(backButton);
        this.add(Box.createVerticalGlue());
    }

    // MODIFIES: this
    // EFFECTS: attempts to load the graph from the file name in the textbox
    private void onSubmit(JTextField textbox) {
        try {
            JSONObject data = ReadWriteJson.readJson(textbox.getText());
            Graph graph = ParseJson.toGraph(data);
            if (graph.getRows() == graph.getCols()) {
                gui.getGraphMenu().setGraph(graph);
                gui.setAlgorithm(ParseJson.toAlgorithm(data, graph));
                gui.showMenu(Menu.GRAPH);
            }

        } catch (IOException | UncheckedIOException e) {
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
