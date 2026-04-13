import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class: CLIHandler
 * <p>
 * Purpose:
 * Provides a command-line interface for interacting with the Video Game Collection system.
 * <p>
 * Primary Functionality:
 * Handles user input, displays menus, and calls methods from GameCollection to manage
 * VideoGame objects (add, delete, update, load, and calculate inflation).
 * <p>
 * Role in System:
 * Acts as the user-facing controller for the non-GUI version of the application.
 * <p>
 * Dependencies:
 * Relies on GameCollection and VideoGame classes for all data operations.
 */
public class CLIHandler {

    // Initialize new GameCollection object
    GameCollection collection = new GameCollection();
    // Initialize Scanner for user input
    Scanner scanner = new Scanner(System.in);

    /**
     * Displays the main menu and processes user input continuously until exit is selected.
     *
     * @return returns 0 when the user chooses to quit the application.
     */
    public int menu() {

        // Loop until the user chooses to quit
        while(true) {

            // Display main menu options
            System.out.println("*****************************************");
            System.out.println("*       Video Game Collection DMS       *");
            System.out.println("*****************************************");
            System.out.println("* 1. View Entire Collection             *");
            System.out.println("* 2. Add New Game                       *");
            System.out.println("* 3. Delete Game                        *");
            System.out.println("* 4. Update Game                        *");
            System.out.println("* 5. Calculate Inflation Price          *");
            System.out.println("* 6. Load Games From File               *");
            System.out.println("* 7. Quit                               *");
            System.out.println("*****************************************");

            // Get user menu selection
            String input = scanner.nextLine();

            switch(input) {

                // View entire collection
                case "1":
                    System.out.println("*****************************************");
                    System.out.println("*          Video Game Collection        *");
                    System.out.println("*****************************************");
                    collection.printCollection();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;

                // Add new game
                case "2":
                    addNewGame();
                    break;

                // Delete a game
                case "3":
                    deleteGame();
                    break;

                // Update a game
                case "4":
                    updateGame();
                    break;

                // Calculate inflation price
                case "5":
                    calculateInflation();
                    break;

                // Load games from file
                case "6":
                    loadGamesFromFile();
                    break;

                // Quit the program
                case "7":
                    System.out.println("Exiting...");
                    return 0;

                // Invalid selection
                default:
                    System.out.println("Invalid Selection. Try Again...");
                    break;
            }

        }

    }

    /**
     * Prompts the user to enter details for a new VideoGame and adds it to the collection.
     * Performs validation for ID uniqueness, numeric constraints, and required fields.
     */
    private void addNewGame() {

        int id;
        while(true){
            System.out.print("Video Game ID: ");
            String idInput = scanner.nextLine();
            if(idInput.isEmpty()){
                System.out.println("ID cannot be blank.");
                continue;
            }
            try{
                id = Integer.parseInt(idInput);
                if(id <= 0){
                    System.out.println("ID must be greater than 0.");
                    continue;
                }
                if(collection.searchByGameID(id) != null){
                    System.out.println("ID already exists.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Invalid numeric input.");
            }
        }

        System.out.print("Title: ");
        String title = scanner.nextLine();
        while(title.isEmpty()){
            System.out.print("Title cannot be blank: ");
            title = scanner.nextLine();
        }

        if(collection.searchByGameTitle(title) != null){
            System.out.print("Game title already exists. Continue? (true/false): ");
            String confirm = scanner.nextLine();
            while(!confirm.equalsIgnoreCase("true") && !confirm.equalsIgnoreCase("false")){
                System.out.print("Enter true or false: ");
                confirm = scanner.nextLine();
            }
            if(confirm.equalsIgnoreCase("false")){
                return;
            }
        }

        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        while(genre.isEmpty()){
            System.out.print("Genre cannot be blank: ");
            genre = scanner.nextLine();
        }

        System.out.print("Platform: ");
        String platform = scanner.nextLine();
        while(platform.isEmpty()){
            System.out.print("Platform cannot be blank: ");
            platform = scanner.nextLine();
        }

        int year;
        while(true){
            System.out.print("Release Year: ");
            String yearInput = scanner.nextLine();
            if(yearInput.isEmpty()){
                System.out.println("Year cannot be blank.");
                continue;
            }
            try{
                year = Integer.parseInt(yearInput);
                int currentYear = java.time.Year.now().getValue();
                if(year < 1958 || year > currentYear){
                    System.out.println("Year must be between 1958 and " + currentYear);
                } else {
                    break;
                }
            }catch(Exception e){
                System.out.println("Invalid numeric input.");
            }
        }

        double price;
        while(true){
            System.out.print("Release Price: ");
            String priceInput = scanner.nextLine();
            if(priceInput.isEmpty()){
                System.out.println("Price cannot be blank.");
                continue;
            }
            try{
                price = Double.parseDouble(priceInput);
                if(price < 0){
                    System.out.println("Price cannot be negative.");
                } else {
                    break;
                }
            }catch(Exception e){
                System.out.println("Invalid numeric input.");
            }
        }

        Boolean multiplayer = null;
        while(multiplayer == null){
            System.out.print("Multiplayer (true/false): ");
            String multiInput = scanner.nextLine();
            if(multiInput.equalsIgnoreCase("true")){
                multiplayer = true;
            } else if(multiInput.equalsIgnoreCase("false")){
                multiplayer = false;
            } else {
                System.out.println("Enter true or false.");
            }
        }

        collection.addGame(new VideoGame(id,title,genre,platform,year,price,multiplayer));
        System.out.println("Game successfully added.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Allows the user to delete a game from the collection by title or ID.
     */
    private void deleteGame() {

        System.out.println("*****************************************");
        System.out.println("*             Delete a Game             *");
        System.out.println("*****************************************");
        System.out.println("* 1. Delete by Title                    *");
        System.out.println("* 2. Delete by ID                       *");
        System.out.println("* 3. Exit                               *");
        System.out.println("*****************************************");

        String input = scanner.nextLine();

        switch(input){
            case "1":
                System.out.print("Enter Title: ");
                String delTitle = scanner.nextLine();
                while(delTitle.isEmpty()){
                    System.out.print("Title cannot be blank: ");
                    delTitle = scanner.nextLine();
                }
                if(collection.deleteGameByTitle(delTitle)){
                    System.out.println("Game deleted.");
                } else {
                    System.out.println("Game not found.");
                }
                break;

            case "2":
                int delID;
                while(true){
                    System.out.print("Enter Game ID: ");
                    String idInput = scanner.nextLine();
                    if(idInput.isEmpty()){
                        System.out.println("ID cannot be blank.");
                        continue;
                    }
                    try{
                        delID = Integer.parseInt(idInput);
                        break;
                    }catch(Exception e){
                        System.out.println("Invalid numeric input.");
                    }
                }
                VideoGame game = collection.searchByGameID(delID);
                if(game != null){
                    collection.deleteGameByID(delID);
                    System.out.println(game.getGameTitle() + " has been deleted.");
                } else {
                    System.out.println("Game not found.");
                }
                break;
        }

        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Provides a submenu for updating different attributes of a selected game.
     * Supports updates by either title or ID.
     */
    private void updateGame() {

        System.out.println("*****************************************");
        System.out.println("*             Update a Game             *");
        System.out.println("*****************************************");
        System.out.println("* 1. Update by Title                     *");
        System.out.println("* 2. Update by ID                        *");
        System.out.println("* 3. Exit                                *");
        System.out.println("*****************************************");

        String input = scanner.nextLine();
        VideoGame gameToUpdate = null;

        if(input.equals("1")){
            System.out.print("Enter the title of the game to update: ");
            String upTitle = scanner.nextLine();
            while(upTitle.isEmpty()){
                System.out.print("Title cannot be blank. Enter the title: ");
                upTitle = scanner.nextLine();
            }
            gameToUpdate = collection.searchByGameTitle(upTitle);

        } else if(input.equals("2")){
            int upID = -1;
            System.out.print("Enter the ID of the game to update: ");
            while(upID <= 0){
                if(scanner.hasNextInt()){
                    upID = scanner.nextInt();
                    scanner.nextLine();
                    if(upID <= 0){
                        System.out.print("ID must be greater than 0. Enter ID: ");
                    }
                } else {
                    System.out.print("Invalid input. Enter numeric ID: ");
                    scanner.nextLine();
                }
            }
            gameToUpdate = collection.searchByGameID(upID);
        }

        if(gameToUpdate == null){
            System.out.println("Game not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        boolean updating = true;
        while(updating){

            System.out.println("*****************************************");
            System.out.println("Updating: " + gameToUpdate.getGameTitle());
            System.out.println("*****************************************");
            System.out.println("* 1. Game ID                            *");
            System.out.println("* 2. Title                              *");
            System.out.println("* 3. Genre                              *");
            System.out.println("* 4. Platform                           *");
            System.out.println("* 5. Release Year                       *");
            System.out.println("* 6. Release Price                      *");
            System.out.println("* 7. Multiplayer                        *");
            System.out.println("* 8. Exit                               *");
            System.out.println("*****************************************");

            input = scanner.nextLine();

            switch(input){
                case "1":
                    int newGameID = -1;
                    while(true){
                        System.out.print("New Game ID: ");
                        String idInput = scanner.nextLine();
                        if(idInput.isEmpty()){
                            System.out.println("ID cannot be blank.");
                            continue;
                        }
                        try{
                            newGameID = Integer.parseInt(idInput);
                            if(newGameID <= 0){
                                System.out.println("ID must be > 0.");
                                continue;
                            }
                            if(!collection.updateGameIDByID(gameToUpdate.getGameID(), newGameID)){
                                System.out.println("ID already exists. Enter another.");
                            } else break;
                        }catch(Exception e){
                            System.out.println("Invalid numeric input.");
                        }
                    }
                    break;

                case "2":
                    System.out.print("New Title: ");
                    String newTitle = scanner.nextLine();
                    while(newTitle.isEmpty()){
                        System.out.print("Title cannot be blank: ");
                        newTitle = scanner.nextLine();
                    }
                    collection.updateGameTitleByID(gameToUpdate.getGameID(), newTitle);
                    break;

                case "3":
                    System.out.print("New Genre: ");
                    String newGenre = scanner.nextLine();
                    while(newGenre.isEmpty()){
                        System.out.print("Genre cannot be blank: ");
                        newGenre = scanner.nextLine();
                    }
                    collection.updateGameGenreByID(gameToUpdate.getGameID(), newGenre);
                    break;

                case "4":
                    System.out.print("New Platform: ");
                    String newPlatform = scanner.nextLine();
                    while(newPlatform.isEmpty()){
                        System.out.print("Platform cannot be blank: ");
                        newPlatform = scanner.nextLine();
                    }
                    collection.updateGamePlatformByID(gameToUpdate.getGameID(), newPlatform);
                    break;

                case "5":
                    int newYear = -1;
                    while(true){
                        System.out.print("New Release Year: ");
                        String yearInput = scanner.nextLine();
                        if(yearInput.isEmpty()){
                            System.out.println("Year cannot be blank.");
                            continue;
                        }
                        try{
                            newYear = Integer.parseInt(yearInput);
                            int currentYear = java.time.Year.now().getValue();
                            if(newYear < 1958 || newYear > currentYear){
                                System.out.println("Year must be between 1958 and " + currentYear);
                            } else break;
                        }catch(Exception e){
                            System.out.println("Invalid numeric input.");
                        }
                    }
                    collection.updateGameYearByID(gameToUpdate.getGameID(), newYear);
                    break;

                case "6":
                    double newPrice = -1;
                    while(true){
                        System.out.print("New Release Price: ");
                        String priceInput = scanner.nextLine();
                        if(priceInput.isEmpty()){
                            System.out.println("Price cannot be blank.");
                            continue;
                        }
                        try{
                            newPrice = Double.parseDouble(priceInput);
                            if(newPrice < 0){
                                System.out.println("Price cannot be negative.");
                            } else break;
                        }catch(Exception e){
                            System.out.println("Invalid numeric input.");
                        }
                    }
                    collection.updateGamePriceByID(gameToUpdate.getGameID(), newPrice);
                    break;

                case "7":
                    Boolean newMulti = null;
                    while(newMulti == null){
                        System.out.print("Multiplayer (true/false): ");
                        String multiInput = scanner.nextLine();
                        if(multiInput.equalsIgnoreCase("true")) newMulti = true;
                        else if(multiInput.equalsIgnoreCase("false")) newMulti = false;
                        else System.out.println("Enter true or false.");
                    }
                    collection.updateGameMultiplayerByID(gameToUpdate.getGameID(), newMulti);
                    break;

                case "8":
                    updating = false;
                    break;

                default:
                    System.out.println("Invalid selection.");
                    break;
            }
        }

        System.out.println("Game update complete. Press Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Calculates and displays the inflation-adjusted price of a selected game.
     */
    private void calculateInflation() {

        System.out.println("*****************************************");
        System.out.println("*    Video Game Inflation Calculator    *");
        System.out.println("*****************************************");
        System.out.println("* 1. Select Game Via Title              *");
        System.out.println("* 2. Select Game Via GameID             *");
        System.out.println("* 3. Exit                               *");
        System.out.println("*****************************************");

        String input = scanner.nextLine();
        VideoGame gameToCalc;

        switch(input){
            case "1":
                System.out.print("Video Game Title: ");
                String titleCalc = scanner.nextLine();
                while(titleCalc.isEmpty()){
                    System.out.print("Title cannot be blank: ");
                    titleCalc = scanner.nextLine();
                }
                gameToCalc = collection.searchByGameTitle(titleCalc);
                if(gameToCalc != null){
                    double inflatedPrice = gameToCalc.calculateInflationPrice();
                    System.out.println("Game: " + gameToCalc.getGameTitle());
                    System.out.println("Original Price: $" + gameToCalc.getGameReleasePrice());
                    System.out.println("Inflation Adjusted Price: $" + inflatedPrice);
                } else {
                    System.out.println("Game not found.");
                }
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                break;

            case "2":
                int idCalc = -1;
                while(true){
                    System.out.print("Video Game ID: ");
                    String idInput = scanner.nextLine();
                    if(idInput.isEmpty()){
                        System.out.println("ID cannot be blank.");
                        continue;
                    }
                    try{
                        idCalc = Integer.parseInt(idInput);
                        break;
                    }catch(Exception e){
                        System.out.println("Invalid numeric input.");
                    }
                }
                gameToCalc = collection.searchByGameID(idCalc);
                if(gameToCalc != null){
                    double inflatedPrice = gameToCalc.calculateInflationPrice();
                    System.out.println("Game: " + gameToCalc.getGameTitle());
                    System.out.println("Original Price: $" + gameToCalc.getGameReleasePrice());
                    System.out.println("Inflation Adjusted Price: $" + inflatedPrice);
                } else {
                    System.out.println("Game not found.");
                }
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                break;

            case "3":
                break;
        }

    }

    /**
     * Loads games from a file into the collection.
     *
     * @return number of games successfully loaded from the file
     */
    public int loadGamesFromFile(){

        while(true){

            System.out.println("*****************************************");
            System.out.println("*        Load Games From File           *");
            System.out.println("*****************************************");
            System.out.println("* 1. Enter Filepath                     *");
            System.out.println("* 2. Exit                               *");
            System.out.println("*****************************************");

            String input = scanner.nextLine();

            switch(input){

                case "1":
                    System.out.print("Enter Filepath (.txt): ");
                    String filepath = scanner.nextLine();
                    while(filepath.isEmpty()){
                        System.out.print("Filepath cannot be blank: ");
                        filepath = scanner.nextLine();
                    }
                    List<String> lines = new ArrayList<>();
                    int added = collection.fileLoader(filepath, lines, collection);
                    System.out.println("Successfully added " + added + " games.");

                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;

                case "2":
                    return 0;
            }
        }
    }
}