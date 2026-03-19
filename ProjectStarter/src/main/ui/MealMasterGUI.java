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
        addButtonPanel();
        addMenuBar();

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

    /**
	 * Helper to add control buttons.
	 */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(new JButton(new AddRecipeAction()));
        buttonPanel.add(new JButton(new ViewRecipesAction()));
        buttonPanel.add(new JButton(new SearchRecipesAction()));
        buttonPanel.add(new JButton(new GroceryListAction()));
        buttonPanel.add(new JButton(new WeeklyScheduleAction()));
        buttonPanel.add(createPrintCombo());

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    /**
	 * Helper to create print options combo box
	 * @return  the combo box
	 */
	private JComboBox<String> createPrintCombo() {
		printCombo = new JComboBox<String>();
		printCombo.addItem("File");
		printCombo.addItem("Screen");
		return printCombo;
	}

    // EFFECTS: adds menu bar
    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu recipeMenu = new JMenu("Recipes");
        recipeMenu.add(new JMenuItem(new AddRecipeAction()));
        recipeMenu.add(new JMenuItem(new ViewRecipesAction()));
        recipeMenu.add(new JMenuItem(new SearchRecipesAction()));
        recipeMenu.add(new JMenuItem(new GroceryListAction()));
        recipeMenu.add(new JMenuItem(new WeeklyScheduleAction()));

        menuBar.add(recipeMenu);

        setJMenuBar(menuBar);
    }
}

