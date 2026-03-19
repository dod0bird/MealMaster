package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.*;


// this ui code was inspiried by the code in AlarmSystem.

/**
 * Represents application's main window frame.
 */
@ExcludeFromJacocoGeneratedReport
public class MealMasterGUI extends JFrame {
    private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

    private JDesktopPane desktop;
	private JInternalFrame controlPanel;

    private JTextArea recipeArea;
    private JComboBox<String> printCombo;

    private RecipeBook rb;

    // EFFECTS: constructor creates the main window
    public MealMasterGUI() {
        super("Meal Master GUI");

        rb = new RecipeBook();

        desktop = new JDesktopPane();
        setContentPane(desktop);
        
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setSize(600, 400);

        addRecipePanel();

        desktop.add(controlPanel);
        controlPanel.setVisible(true);

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		setVisible(true);
    }
    
    private void addRecipePanel() {
        recipeArea = new JTextArea();
        recipeArea.setEditable(false);
        recipeArea.setLineWrap(true);
        recipeArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(recipeArea);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        controlPanel.add(scrollPane, BorderLayout.CENTER);
    }
}
