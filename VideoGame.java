import java.time.Year;
/**
 * PURPOSE:
 * Represents a video game and its associated attributes.
 * <p>
 * FUNCTIONALITY:
 * This class stores information about a video game, including its ID, title,
 * genre, platform, release year, release price, and multiplayer capability.
 * It provides getter and setter methods to access and modify this data, as well
 * as a method to calculate the inflation-adjusted price.
 * <p>
 * ROLE IN SYSTEM:
 * Acts as a data model for video game objects used throughout the application,
 * particularly for storage, retrieval, and display in the GUI.
 * <p>
 * RELATIONSHIPS:
 * Used by database and GUI classes to manage and present video game data.
 */
public class VideoGame {

    // VideoGame attributes
    private int gameID;
    private String gameTitle;
    private String gameGenre;
    private String gamePlatform;
    private int gameReleaseYear;
    private double gameReleasePrice;
    private boolean multiplayer;

    /**
     * Creates a new VideoGame object with the specified attributes.
     *
     * @param gameID The unique identifier for the video game.
     * @param gameTitle The title of the video game.
     * @param gameGenre The genre of the video game.
     * @param gamePlatform The platform the game is available on (e.g., PC, Xbox).
     * @param gameReleaseYear The year the game was released.
     * @param gameReleasePrice The original release price of the game.
     * @param multiplayer Indicates whether the game supports multiplayer.
     */
    public VideoGame(
            int gameID,
            String gameTitle,
            String gameGenre,
            String gamePlatform,
            int gameReleaseYear,
            double gameReleasePrice,
            boolean multiplayer
    ){
        this.gameID = gameID;
        this.gameTitle = gameTitle;
        this.gameGenre = gameGenre;
        this.gamePlatform = gamePlatform;
        this.gameReleaseYear = gameReleaseYear;
        this.gameReleasePrice = gameReleasePrice;
        this.multiplayer = multiplayer;
    }

    /**
     * Returns the unique identifier of the video game.
     *
     * @return The gameID value.
     */
    public int getGameID(){
        return gameID;
    }

    /**
     * Sets the unique identifier for the video game.
     *
     * @param gameID The unique identifier to assign.
     */
    public void setGameID(int gameID){
        this.gameID = gameID;
    }

    /**
     * Returns the title of the video game.
     *
     * @return The gameTitle value.
     */
    public String getGameTitle(){
        return gameTitle;
    }

    /**
     * Sets the title of the video game.
     *
     * @param gameTitle The title to assign.
     */
    public void setGameTitle(String gameTitle){
        this.gameTitle = gameTitle;
    }

    /**
     * Returns the genre of the video game.
     *
     * @return The gameGenre value.
     */
    public String getGameGenre(){
        return gameGenre;
    }

    /**
     * Sets the genre of the video game.
     *
     * @param gameGenre The genre to assign.
     */
    public void setGameGenre(String gameGenre){
        this.gameGenre = gameGenre;
    }

    /**
     * Returns the platform the video game is available on.
     *
     * @return The gamePlatform value.
     */
    public String getGamePlatform(){
        return gamePlatform;
    }

    /**
     * Sets the platform of the video game.
     *
     * @param gamePlatform The platform to assign.
     */
    public void setGamePlatform(String gamePlatform){
        this.gamePlatform = gamePlatform;
    }

    /**
     * Returns the release year of the video game.
     *
     * @return The gameReleaseYear value.
     */
    public int getGameReleaseYear(){
        return gameReleaseYear;
    }

    /**
     * Sets the release year of the video game.
     *
     * @param gameReleaseYear The year to assign.
     */
    public void setGameReleaseYear(int gameReleaseYear){
        this.gameReleaseYear = gameReleaseYear;
    }

    /**
     * Returns the original release price of the video game.
     *
     * @return The gameReleasePrice value.
     */
    public double getGameReleasePrice(){
        return gameReleasePrice;
    }

    /**
     * Sets the release price of the video game.
     *
     * @param gameReleasePrice The price to assign.
     */
    public void setGameReleasePrice(double gameReleasePrice){
        this.gameReleasePrice = gameReleasePrice;
    }

    /**
     * Returns whether the video game supports multiplayer.
     *
     * @return true if multiplayer is supported, false otherwise.
     */
    public boolean isMultiplayer(){
        return multiplayer;
    }

    /**
     * Sets whether the video game supports multiplayer.
     *
     * @param multiplayer true if multiplayer is supported, false otherwise.
     */
    public void setMultiplayer(boolean multiplayer){
        this.multiplayer = multiplayer;
    }

    /**
     * Calculates the inflation-adjusted price of the game based on an
     * estimated average annual inflation rate of 3%.
     *
     * @return The inflation-adjusted price rounded to two decimal places.
     */
    public double calculateInflationPrice(){
        if (gameReleasePrice < 0) {
            return 0;
        }
        int year = Year.now().getValue();
        double averageAnnualInflation = 0.03;
        double inflationPrice = (gameReleasePrice *
                (Math.pow((1 + averageAnnualInflation),
                        (year - gameReleaseYear))));

        return Math.round(inflationPrice * 100.0) / 100.0;
    }

    /**
     * Returns a string representation of the VideoGame object.
     *
     * @return A formatted string containing all video game attributes.
     */
    @Override
    public String toString() {
        return "GameID: " + gameID +
                " | Title: " + gameTitle +
                " | Genre: " + gameGenre +
                " | Platform: " + gamePlatform +
                " | Year: " + gameReleaseYear +
                " | Price: " + gameReleasePrice +
                " | Multiplayer: " + multiplayer;
    }
}