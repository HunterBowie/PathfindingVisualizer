package ui.menu;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.SwingGUI;

/**
 * Repersents the menu shown at the start the program
 **/
public class StartMenu extends JPanel {
    
    private SwingGUI gui;
    
    // EFFECTS: creates a new StartMenu with given gui
    public StartMenu(SwingGUI gui) {
        initComponents();
        this.gui = gui;
    }

    // MODIFIES: this
    // EFFECTS: adds all the components to the menu
    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createVerticalGlue());
        JLabel title = new JLabel("Would you like to create a new graph or load an existing one?");
        add(title);
        JButton loadButton = new JButton("Load Graph");
        loadButton.addActionListener(_ -> gui.showMenu(Menu.ENTER_FILE));
        add(loadButton);
        JButton createButton = new JButton("Create Graph");
        createButton.addActionListener(_ -> gui.showMenu(Menu.CREATE_GRAPH));
        add(createButton);
        this.add(Box.createVerticalGlue());
    }

    @Override
    public Component add(Component component) {
        ((JComponent) component).setAlignmentX(Component.CENTER_ALIGNMENT);
        return super.add(component);
    }
}
