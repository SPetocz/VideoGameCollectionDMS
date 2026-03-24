import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GameCollection {

    //game collection contains a single attribute of an ArrayList<VideoGames>
    public ArrayList<VideoGame> games;

    //game collection constructor creates new ArrayList
    public GameCollection() {
        games = new ArrayList<>();
    }

    //method to print collection, returns 0 if prints, 1 if collection is empty
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

    //method to search by game title returns game object or null if not found
    public VideoGame searchByGameTitle(String title) {
        for (VideoGame game : games) {
            if (game.getGameTitle().equalsIgnoreCase(title)) {
                return game;
            }
        }
        return null;
    }

    //method to search by game id returns game object or null if not found
    public VideoGame searchByGameID(int gameID) {
        for (VideoGame game : games) {
            if (game.getGameID() == gameID) {
                return game;
            }
        }
        return null;
    }

    //method to add new game, returns false if fails, and true if success
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

    //method to delete game by ID, returns true/false for success
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

    //method to delete game by title, returns true/false for success
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

    //methods to update games attributes by title or id, returns true/false for success
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

    public boolean updateGameMultiplayerByTitle(String title, boolean newMultiplayer) {
        VideoGame gameToUpdate = searchByGameTitle(title);
        if (gameToUpdate == null) {
            return false;
        }
        gameToUpdate.setMultiplayer(newMultiplayer);
        return true;
    }

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

    public boolean updateGameMultiplayerByID(int gameID, boolean newMultiplayer) {
        VideoGame gameToUpdate = searchByGameID(gameID);
        if (gameToUpdate == null) {
            return false;
        }
        gameToUpdate.setMultiplayer(newMultiplayer);
        return true;
    }

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