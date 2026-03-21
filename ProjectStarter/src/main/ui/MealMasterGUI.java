package ui;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.*;
import persistence.*;

import java.util.*;
import java.util.List;


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
    private JList<String> recipeList;
    private DefaultListModel<String> listModel;

    private RecipeBook rb;

    private static final String JSON = "./data/mealMaster.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: constructor creates the main window
    public MealMasterGUI() {
        super("Meal Master GUI");
        showLoadingScreen();
        rb = new RecipeBook();
        jsonReader = new JsonReader(JSON);
        jsonWriter = new JsonWriter(JSON);
        desktop = new JDesktopPane();
        setContentPane(desktop);
        controlPanel = new JInternalFrame("Meal Master", false, false, false, false);
        controlPanel.setSize(1000, 400);
        addRecipePanel();
        addButtonPanel();
        addMenuBar();
        loadRecipeBook();
        desktop.add(controlPanel);
        controlPanel.setVisible(true);
        setSize(WIDTH, HEIGHT);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { 
                saveOnExit();
            }
        });
	setVisible(true);
    }
    
    private void addRecipePanel() {
        listModel = new DefaultListModel<>();
        recipeList = new JList<>(listModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        recipeArea = new JTextArea();
        recipeArea.setEditable(false);
        recipeArea.setLineWrap(true);
        recipeArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(recipeArea);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        controlPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void updateRecipeDisplay() {
        recipeArea.setText("");

        for (Recipe r : rb.getRecipes()) {
            recipeArea.append("| name: " + r.getRecipeName() + " | cuisine: " + r.getCuisineType() + " | time: " 
                    + r.getCookingTime() + " | cost: $" + r.getCost() + "\n");
        }

        if (rb.getRecipes().isEmpty()) {
            recipeArea.setText("No recipes avaliable.");
        }
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

        controlPanel.add(buttonPanel, BorderLayout.WEST);
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
        

    private void showLoadingScreen() {
        JWindow splash = new JWindow();

        ImageIcon loadingImage = new ImageIcon("data/IMG_2632.jpg");
        JLabel imageLabel = new JLabel(loadingImage);
        splash.getContentPane().add(imageLabel);

        splash.pack();
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splash.dispose();
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    /**
	 * Represents the action to be taken when the user wants to view all
	 * recipes.
	 */
    private class ViewRecipesAction extends AbstractAction {
        ViewRecipesAction() {
            super("View Recipes");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            updateRecipeDisplay();
        }
    }

    /**
	 * Represents the action to be taken when the user wants to add a new
	 * recipe to the book. 
	 */
    private class AddRecipeAction extends AbstractAction {

        AddRecipeAction() {
            super("Add Recipe");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = JOptionPane.showInputDialog("Enter recipe name:");
                int time = Integer.parseInt(JOptionPane.showInputDialog("Enter cooking time (minutes):"));
                String cuisine = JOptionPane.showInputDialog("Enter cuisine type:");
                double cost = Double.parseDouble(JOptionPane.showInputDialog("Enter cost ($):"));
                Recipe recipe = new Recipe(name, time, cuisine, cost);

                while (true) {
                    String ingName = JOptionPane.showInputDialog("Ingredient name (or 'done'):");
                    if (ingName.equalsIgnoreCase("done")) {
                        break;
                    }

                    double qty = Double.parseDouble(JOptionPane.showInputDialog("Quantity:"));
                    String unit = JOptionPane.showInputDialog("Unit:");

                    recipe.addIngredient(new Ingredient(ingName, qty, unit));
                }
                
                rb.addRecipe(recipe);
                updateRecipeDisplay();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input.", "System Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
	 * Represents the action to be taken when the user wants to search
	 * recipes.
	 */
    private class SearchRecipesAction extends AbstractAction {
        SearchRecipesAction() {
            super("Search Recipe");
        }
            
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] options = {"ingredient", "cuisine", "max time", "max cost"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Search by:",
                    "Search",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
            try {
                List<Recipe> results = getSearchResults(choice);
                displayResults(results);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input.", "System Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private List<Recipe> getSearchResults(int choice) {
            List<Recipe> results = new ArrayList<>();

            if (choice == 0) {
                String input = JOptionPane.showInputDialog("Enter ingredient name: ");
                results = rb.searchByIngredient(input);

            } else if (choice == 1) {
                String input = JOptionPane.showInputDialog("Enter cuisine type: ");
                results = rb.searchByCuisine(input);

            } else if (choice == 2) {
                int input = Integer.parseInt(
                        JOptionPane.showInputDialog("Enter maximum cooking time (mins): "));
                results = rb.searchByCookingTime(input);

            } else if (choice == 3) {
                Double input = Double.parseDouble(
                        JOptionPane.showInputDialog("Enter maximum budget: "));
                results = rb.searchByCost(input);
            }
            return results;
        }

        private void displayResults(List<Recipe> result) {
            if (result.isEmpty()) {
                recipeArea.setText("No recipes found.");
                return;
            }

            recipeArea.setText("Search Results: \n");
            for (Recipe r : result) {
                recipeArea.append("| name: " + r.getRecipeName() + " | cuisine: " + r.getCuisineType() 
                        + " | cooking time: " + r.getCookingTime() + " | min cost: $" + r.getCost());
            }
        }
    }

    /**
	 * Represents the action to be taken when the user wants to create
	 * a grocery list. 
	 */
    private class GroceryListAction extends AbstractAction {
        GroceryListAction() {
            super("Create Grocery List");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Ingredient> groceryList = rb.generateGroceryList(rb.getRecipes());

            recipeArea.setText("Grocery List: \n");
             
            for (Ingredient i : groceryList) {
                recipeArea.append("- " + i.getIngredientQuantity() + " " + i.getIngredientUnit() 
                        + " " + i.getIngredientName() + "\n");
            }

            if (groceryList.isEmpty()) {
                recipeArea.setText("\"No ingredients found.");
            }
        }
    }

    /**
	 * Represents the action to be taken when the user wants to create
	 * a weekly schedule. 
	 */
    private class WeeklyScheduleAction extends AbstractAction {
        WeeklyScheduleAction() {
            super("Create Weekly Schedule");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int[] timeLimits = new int[7];

                for (int i = 0; i < 7; i++) {
                    int input = Integer.parseInt(JOptionPane.showInputDialog(
                            "Enter daily time limits (mins) for day: " + (i + 1)));
                    timeLimits[i] = input;
                }

                double budget = Double.parseDouble(JOptionPane.showInputDialog("Enter maximum weekly budget: "));
                List<Recipe> schedule = rb.generateWeeklySchedule(timeLimits, budget);

                int day = 1;
                for (Recipe r : schedule) {
                    recipeArea.append("Day " + day + ": " + r.getRecipeName() + "|");
                    day++;
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input.", "System Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadRecipeBook() {
        int response = JOptionPane.showConfirmDialog(null,
                "Load recipe collection from file?",
                "load data",
                JOptionPane.YES_NO_OPTION);
        
        if (response == JOptionPane.YES_NO_OPTION) {
            try {
                rb = jsonReader.read();
                updateRecipeDisplay();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Unable to read from file:", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveOnExit() {
        int response = JOptionPane.showConfirmDialog(null,
                "Save recipe book before exiting?",
                "save data",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (response == JOptionPane.YES_NO_CANCEL_OPTION) {
            try {
                jsonWriter.open();
                jsonWriter.write(rb);
                jsonWriter.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Unable to save file:", "System Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        System.exit(0);
    }
}