import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Class: GameCollection
 * <p>
 * Purpose:
 * Stores and manages a collection of VideoGame objects using an ArrayList.
 * <p>
 * Primary Functionality:
 * Provides methods to add, delete, search, update, and load VideoGame objects.
 * <p>
 * Role in System:
 * Acts as the in-memory data management layer of the application.
 * <p>
 * Relationships/Dependencies:
 * Depends on the VideoGame class for storing game objects.
 */
public class GameCollection {

    //game collection contains a single attribute of an ArrayList<VideoGames>
    public ArrayList<VideoGame> games;

    /**
     * Constructs a new GameCollection and initializes the internal ArrayList.
     */
    public GameCollection() {
        games = new ArrayList<>();
    }

    /**
     * Prints all VideoGame objects in the collection to the console.
     *
     * @return 0 if the collection was printed successfully, 1 if the collection is empty.
     */
    public int printCollection() {
        if (games.isEmpty()) {
            System.out.println("Video Game Collection is empty.");
            return 1;
        } else {
            for (VideoGame game : games) {
                System.out.println(game);
            }
            return 0;
        }
    }

    /**
     * Searches for a VideoGame in the collection by its title.
     *
     * @param title The title of the game to search for.
     * @return The matching VideoGame object, or null if not found.
     */
    public VideoGame searchByGameTitle(String title) {
        for (VideoGame game : games) {
            if (game.getGameTitle().equalsIgnoreCase(title)) {
                return game;
            }
        }
        return null;
    }

    /**
     * Searches for a VideoGame in the collection by its ID.
     *
     * @param gameID The ID of the game to search for.
     * @return The matching VideoGame object, or null if not found.
     */
    public VideoGame searchByGameID(int gameID) {
        for (VideoGame game : games) {
            if (game.getGameID() == gameID) {
                return game;
            }
        }
        return null;
    }

    /**
     * Adds a new VideoGame to the collection after validating input data.
     *
     * @param newGame The VideoGame object to add.
     * @return true if the game was successfully added, false otherwise.
     */
    public boolean addGame(VideoGame newGame) {
        int currentYear = java.time.Year.now().getValue();
        if (newGame == null) {
            return false;
        }
        else if (searchByGameID(newGame.getGameID()) != null) {
            return false;
        }
        else if(newGame.getGameID() < 0){
            return false;
        }
        else if(newGame.getGameReleaseYear() < 1958 || newGame.getGameReleaseYear() > currentYear){
            return false;
        }
        else if (newGame.getGameReleasePrice() < 0) {
            return false;
        } else {
            games.add(newGame);
            games.sort(Comparator.comparingInt(VideoGame::getGameID));
            return true;
        }
    }

    /**
     * Deletes a VideoGame from the collection using its ID.
     *
     * @param gameID The ID of the game to delete.
     * @return true if the game was successfully deleted, false otherwise.
     */
    public boolean deleteGameByID(int gameID) {
        VideoGame toDelete = searchByGameID(gameID);
        if (toDelete != null) {
            games.remove(toDelete);
            boolean completed;
            if(searchByGameID(gameID)== null){
                completed = true;
                return true;
            } else {
                completed = false;
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Deletes a VideoGame from the collection using its title.
     *
     * @param title The title of the game to delete.
     * @return true if the game was successfully deleted, false otherwise.
     */
    public boolean deleteGameByTitle(String title) {
        VideoGame toDelete = searchByGameTitle(title);
        if (toDelete != null) {
            games.remove(toDelete);
            boolean completed;
            if(searchByGameTitle(title)== null){
                completed = true;
                return true;
            } else {
                completed = false;
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Updates the game ID for a game found by title.
     *
     * @param title The title of the game to update.
     * @param newID The new ID to assign.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGameIDByTitle(String title, int newID) {
        for (VideoGame game : games) {
            if (game.getGameID() == newID) {
                return false;
            }
        }
        if(newID < 0){
            return false;
        }
        VideoGame gameToUpdate = searchByGameTitle(title);
        if (gameToUpdate == null) {
            return false;
        }
        gameToUpdate.setGameID(newID);
        games.sort(Comparator.comparingInt(VideoGame::getGameID));
        return true;
    }

    /**
     * Updates the title of a game found by title.
     *
     * @param title The current title of the game.
     * @param newTitle The new title to assign.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGameTitleByTitle(String title, String newTitle) {
        VideoGame gameToUpdate = searchByGameTitle(title);
        if (gameToUpdate == null) {
            return false;
        }
        if(newTitle.isBlank()){
            return false;
        }
        gameToUpdate.setGameTitle(newTitle);
        return true;
    }

    /**
     * Updates the genre of a game found by title.
     *
     * @param title The title of the game.
     * @param newGenre The new genre to assign.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGameGenreByTitle(String title, String newGenre) {
        VideoGame gameToUpdate = searchByGameTitle(title);
        if (gameToUpdate == null) {
            return false;
        }
        if(newGenre.isBlank()){
            return false;
        }
        gameToUpdate.setGameGenre(newGenre);
        return true;
    }

    /**
     * Updates the platform of a game found by title.
     *
     * @param title The title of the game.
     * @param newPlatform The new platform to assign.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGamePlatformByTitle(String title, String newPlatform) {
        VideoGame gameToUpdate = searchByGameTitle(title);
        if (gameToUpdate == null) {
            return false;
        }
        if(newPlatform.isBlank()){
            return false;
        }
        gameToUpdate.setGamePlatform(newPlatform);
        return true;
    }

    /**
     * Updates the release year of a game found by title.
     *
     * @param title The title of the game.
     * @param newYear The new release year.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGameYearByTitle(String title, int newYear) {
        VideoGame gameToUpdate = searchByGameTitle(title);
        if (gameToUpdate == null) {
            return false;
        }
        if(newYear < 1958 || newYear > java.time.Year.now().getValue()){
            return false;
        }
        gameToUpdate.setGameReleaseYear(newYear);
        return true;
    }

    /**
     * Updates the price of a game found by title.
     *
     * @param title The title of the game.
     * @param newPrice The new price.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGamePriceByTitle(String title, double newPrice) {
        VideoGame gameToUpdate = searchByGameTitle(title);
        if (gameToUpdate == null) {
            return false;
        }
        if(newPrice < 0){
            return false;
        }
        gameToUpdate.setGameReleasePrice(newPrice);
        return true;
    }

    /**
     * Updates multiplayer status of a game found by title.
     *
     * @param title The title of the game.
     * @param newMultiplayer The new multiplayer value.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGameMultiplayerByTitle(String title, boolean newMultiplayer) {
        VideoGame gameToUpdate = searchByGameTitle(title);
        if (gameToUpdate == null) {
            return false;
        }
        gameToUpdate.setMultiplayer(newMultiplayer);
        return true;
    }

    /**
     * Updates the game ID for a game found by ID.
     *
     * @param gameID The current ID of the game.
     * @param newID The new ID to assign.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGameIDByID(int gameID, int newID) {
        for (VideoGame game : games) {
            if (game.getGameID() == newID) {
                return false;
            }
        }
        VideoGame gameToUpdate = searchByGameID(gameID);
        if(newID == gameID){
            return false;
        }
        if(newID < 0){
            return false;
        }
        if (gameToUpdate == null) {
            return false;
        }
        gameToUpdate.setGameID(newID);
        return true;
    }

    /**
     * Updates the title of a game found by ID.
     *
     * @param gameID The ID of the game.
     * @param newTitle The new title.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGameTitleByID(int gameID, String newTitle) {
        VideoGame gameToUpdate = searchByGameID(gameID);
        if (gameToUpdate == null) {
            return false;
        }
        if(newTitle.isBlank()){
            return false;
        }
        gameToUpdate.setGameTitle(newTitle);
        return true;
    }

    /**
     * Updates the genre of a game found by ID.
     *
     * @param gameID The ID of the game.
     * @param newGenre The new genre.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGameGenreByID(int gameID, String newGenre) {
        VideoGame gameToUpdate = searchByGameID(gameID);
        if (gameToUpdate == null) {
            return false;
        }
        if (newGenre.isBlank()){
            return false;
        }
        gameToUpdate.setGameGenre(newGenre);
        return true;
    }

    /**
     * Updates the platform of a game found by ID.
     *
     * @param gameID The ID of the game.
     * @param newPlatform The new platform.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGamePlatformByID(int gameID, String newPlatform) {
        VideoGame gameToUpdate = searchByGameID(gameID);
        if (gameToUpdate == null) {
            return false;
        }
        if(newPlatform.isBlank()){
            return false;
        }
        gameToUpdate.setGamePlatform(newPlatform);
        return true;
    }

    /**
     * Updates the release year of a game found by ID.
     *
     * @param gameID The ID of the game.
     * @param newYear The new release year.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGameYearByID(int gameID, int newYear) {
        VideoGame gameToUpdate = searchByGameID(gameID);
        if (gameToUpdate == null) {
            return false;
        }
        if(newYear < 1958 || newYear > java.time.Year.now().getValue()){
            return false;
        }
        gameToUpdate.setGameReleaseYear(newYear);
        return true;
    }

    /**
     * Updates the price of a game found by ID.
     *
     * @param gameID The ID of the game.
     * @param newPrice The new price.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGamePriceByID(int gameID, double newPrice) {
        VideoGame gameToUpdate = searchByGameID(gameID);
        if (gameToUpdate == null) {
            return false;
        }
        if(newPrice < 0){
            return false;
        }
        gameToUpdate.setGameReleasePrice(newPrice);
        return true;
    }

    /**
     * Updates multiplayer status of a game found by ID.
     *
     * @param gameID The ID of the game.
     * @param newMultiplayer The new multiplayer value.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateGameMultiplayerByID(int gameID, boolean newMultiplayer) {
        VideoGame gameToUpdate = searchByGameID(gameID);
        if (gameToUpdate == null) {
            return false;
        }
        gameToUpdate.setMultiplayer(newMultiplayer);
        return true;
    }

    /**
     * Loads games from a file into the collection.
     *
     * Each line must follow format:
     * id-title-genre-platform-year-price-multiplayer
     *
     * @param filepath The file path to load from.
     * @param lines List used to store file lines.
     * @param collection The GameCollection to add games into.
     * @return number of successfully added games.
     */
    public int fileLoader(String filepath, List<String> lines, GameCollection collection) {
        int added = 0;
        try {
            Scanner fileScanner = new Scanner(new File(filepath));
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine().trim());
            }
            fileScanner.close();

            for (String line : lines) {
                String[] tokens = line.split("-");
                if (tokens.length != 7) continue;

                try {
                    int id = Integer.parseInt(tokens[0]);
                    String title = tokens[1];
                    String genre = tokens[2];
                    String platform = tokens[3];
                    int year = Integer.parseInt(tokens[4]);
                    double price = Double.parseDouble(tokens[5]);
                    boolean multiplayer = Boolean.parseBoolean(tokens[6]);

                    if (collection.addGame(new VideoGame(id, title, genre, platform, year, price, multiplayer))) {
                        added++;
                    }

                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return added;
    }
}