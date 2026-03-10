import java.util.ArrayList;
import java.util.Comparator;

public class GameCollection {
    //game collection contains a single attribute of an ArrayList<VideoGames>
    public ArrayList<VideoGame> games;

    //game collection constructor creates new ArrayList
    public GameCollection() {
        games = new ArrayList<>();
    }

    //method to print collection, returns 0 if prints, 1 if collection is empty
    public int printCollection(){
        if(games.isEmpty()){
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

    //method ot search by game id returns game object or null if not found
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
        if (newGame == null) {
            return false;
        }
        if(searchByGameID(newGame.getGameID()) != null){
            return false;
        } else {
            games.add(newGame);
            games.sort(Comparator.comparingInt(VideoGame::getGameID));
            return true;
        }

    }

    //method to delete game by ID, returns true/false for success
    public boolean deleteGameByID ( int gameID){
        VideoGame toDelete = searchByGameID(gameID);
        if (toDelete != null) {
            games.remove(toDelete);
            return true;
        } else {
            return false;
        }
    }

    //method to delete game by title, returns true/false for success
    public boolean deleteGameByTitle (String title){
        VideoGame toDelete = searchByGameTitle(title);
        if (toDelete != null) {
            games.remove(toDelete);
            return true;
        } else {
            return false;
        }
    }

    //methods to update games attributes by title or id, returns true/false for success
    public boolean updateGameIDByTitle(String title, int newID){
        for(VideoGame game : games) {
            if(game.getGameID() == newID){
                return false;
            }
        }
            VideoGame gameToUpdate = searchByGameTitle(title);
            if(gameToUpdate == null){
                return false;
            }
            gameToUpdate.setGameID(newID);
            games.sort(Comparator.comparingInt(VideoGame::getGameID));
            return true;
    }

    public boolean updateGameTitleByTitle(String title, String newTitle){
        VideoGame gameToUpdate = searchByGameTitle(title);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGameTitle(newTitle);
        return true;
    }

    public boolean updateGameGenreByTitle(String title, String newGenre){
        VideoGame gameToUpdate = searchByGameTitle(title);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGameGenre(newGenre);
        return true;
    }

    public boolean updateGamePlatformByTitle(String title, String newPlatform){
        VideoGame gameToUpdate = searchByGameTitle(title);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGamePlatform(newPlatform);
        return true;
    }

    public boolean updateGameYearByTitle(String title, int newYear){
        VideoGame gameToUpdate = searchByGameTitle(title);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGameReleaseYear(newYear);
        return true;
    }

    public boolean updateGamePriceByTitle(String title, double newPrice){
        VideoGame gameToUpdate = searchByGameTitle(title);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGameReleasePrice(newPrice);
        return true;
    }

    public boolean updateGameMultiplayerByTitle(String title, boolean newMultiplayer){
        VideoGame gameToUpdate = searchByGameTitle(title);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setMultiplayer(newMultiplayer);
        return true;
    }

    public boolean updateGameIDByID(int gameID, int newID){
        for(VideoGame game : games) {
            if(game.getGameID() == newID){
                return false;
            }
        }
        VideoGame gameToUpdate = searchByGameID(gameID);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGameID(newID);
        return true;
    }

    public boolean updateGameTitleByID(int gameID, String newTitle){
        VideoGame gameToUpdate = searchByGameID(gameID);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGameTitle(newTitle);
        return true;
    }

    public boolean updateGameGenreByID(int gameID, String newGenre){
        VideoGame gameToUpdate = searchByGameID(gameID);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGameGenre(newGenre);
        return true;
    }

    public boolean updateGamePlatformByID(int gameID, String newPlatform){
        VideoGame gameToUpdate = searchByGameID(gameID);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGamePlatform(newPlatform);
        return true;
    }

    public boolean updateGameYearByID(int gameID, int newYear){
        VideoGame gameToUpdate = searchByGameID(gameID);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGameReleaseYear(newYear);
        return true;
    }

    public boolean updateGamePriceByID(int gameID, double newPrice){
        VideoGame gameToUpdate = searchByGameID(gameID);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setGameReleasePrice(newPrice);
        return true;
    }

    public boolean updateGameMultiplayerByID(int gameID, boolean newMultiplayer){
        VideoGame gameToUpdate = searchByGameID(gameID);
        if(gameToUpdate == null){
            return false;
        }
        gameToUpdate.setMultiplayer(newMultiplayer);
        return true;
    }
}