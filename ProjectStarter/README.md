# Meal Master

## A Recipe Keeper and Meal Planner

### **Project Description:**

#### What will the application do?

* Meal Master will **keep track of recipes**. Users can enter recipes with the **required ingredients**, **estimated cooking time**, **cuisine type**, and **budget**. The application can generate a **weekly cooking schedule** based on the time the user has available each day, their budget, and preferred ingredients or cuisine. It can also **create a grocery list** for any missing ingredients. This application will make keeping track of recipes easy, and make meal planning and shopping easier.  


#### Who will use it?
*  Meal Master is designed for anyone who wants to track recipes in a more organized way and save time meal prepping. It is especially helpful for **home cooks**, **busy students** and **families**.


#### Why is this project of interest to you? 
* I love cooking, but keeping track of recipes can be challenging. Planning specific meals based on time availability, ingredients and budget can also be a hassle. I want to create a tool that helps me keep track of the recipes I'd like to try and plan meals around it. 


### User Stories:
- As a user, I want to be able to add a recipe to my collection and specify the recipe name, list of ingredients, estimated cooking time, cuisine type, and cost.  

- As a user, I want to be able to view a list of the recipes in my collection.
- As a user, I want to be able to search for specific recipes by ingredient, estimated cooking time, cuisine type, and cost.  
- As a user, I want to be able to generate a weekly cooking schedule based on the time I have available each day and my budget.
- As a user, I want to be able to generate a grocery list for any  ingredients required for my planned recipes.
- As a user, when I start the application, I want to be given the option to load my recipe collection from file.
- As a user, when I select quit, I want to be given the option to save my recipe collection to file. 

## Instructions for End User

- You can view the panel that displays the recipes added to the book by looking at the right portion of the window
- You can add multiple recipes to a recipe book by using the "Add Recipe" button and entering recipe details.
- You can search for recipes based on cost, cuisine, ingredient, cooking time by clicking "Search Recipe" then clicking on the desired search method
- You can create a weekly cooking schedule by clikcing "Create weekly schedule" then entering your daily time limits in the text box
- You can locate my visual component by launching the application (loading)
- You can save the state of my application by closing the window, and clicking "Yes" when prompted to save the current recipe book
- You can reload the state of my application by running the program and clicking "Yes" when prompted to load the recipe book from file

## Phase 4: task 2
Sat Mar 28 17:45:46 PDT 2026
Added ingredient: noodle to recipe: Pasta

Sat Mar 28 17:45:53 PDT 2026
Added ingredient: tomato sauce to recipe: Pasta

Sat Mar 28 17:46:13 PDT 2026
Added ingredient: noodle to recipe: pasta

Sat Mar 28 17:46:20 PDT 2026
Added ingredient: tomato sauce to recipe: pasta

Sat Mar 28 17:46:27 PDT 2026
Added ingredient: cheese to recipe: pasta

Sat Mar 28 17:46:29 PDT 2026
Added recipe: pasta to recipe book.

Sat Mar 28 17:46:35 PDT 2026
Searched recipes by max cost: 6.0

Sat Mar 28 17:46:41 PDT 2026
Generated grocery list from: 1 recipes.

Sat Mar 28 17:47:05 PDT 2026
Added ingredient: rice to recipe: fried rice

Sat Mar 28 17:47:10 PDT 2026
Added ingredient: spam to recipe: fried rice

Sat Mar 28 17:47:18 PDT 2026
Added ingredient: egg to recipe: fried rice

Sat Mar 28 17:47:19 PDT 2026
Added recipe: fried rice to recipe book.


## Phase 4: Task 3
One improvement I would make is introducing a controller (e.g., RecipeManager) between the UI (MealMasterGUI and MealMasterApp) and the RecipeBook model. Currently the UI interacts directly with the model which works well for a small project and keeps the design straightforward. However, adding an intermediate layer would separate responsibilities by putting application logic outside of the UI. This would make the system easier to extend and maintain if the project were to grow, as changes to fundamental logic would not require modifications across multiple UI components.


I would also refactor the event logging system to decouple it from the model classes. Currently Recipe and RecipeBook classes directly call EventLog.getInstance(), which hardcodes the logging behavior into the model. I would instead use an observer pattern so that logging can be handled externally. This would make the model more reusable and flexible, as it would no longer depend on a global singleton.