import java.time.Year;
public class VideoGame {

    //VideoGame attributes
    private int gameID;
    private String gameTitle;
    private String gameGenre;
    private String gamePlatform;
    private int gameReleaseYear;
    private double gameReleasePrice;
    private boolean multiplayer;

    //VideoGame constructor
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

    //VideoGame getters and setters
    public int getGameID(){
        return gameID;
    }
    public void setGameID(int gameID){
        this.gameID = gameID;
    }
    public String getGameTitle(){
        return gameTitle;
    }
    public void setGameTitle(String gameTitle){
        this.gameTitle = gameTitle;
    }
    public String getGameGenre(){
        return gameGenre;
    }
    public void setGameGenre(String gameGenre){
        this.gameGenre = gameGenre;
    }
    public String getGamePlatform(){
        return gamePlatform;
    }
    public void setGamePlatform(String gamePlatform){
        this.gamePlatform = gamePlatform;
    }
    public int getGameReleaseYear(){
        return gameReleaseYear;
    }
    public void setGameReleaseYear(int gameReleaseYear){
        this.gameReleaseYear = gameReleaseYear;
    }
    public double getGameReleasePrice(){
        return gameReleasePrice;
    }
    public void setGameReleasePrice(double gameReleasePrice){
        this.gameReleasePrice = gameReleasePrice;
    }
    public boolean isMultiplayer(){
        return multiplayer;
    }
    public void setMultiplayer(boolean multiplayer){
        this.multiplayer = multiplayer;
    }
    //Custom method to calculate inflation adjusted price using estimated 3% yearly inflation average
    public double calculateInflationPrice(){
        int year = Year.now().getValue();
        double averageAnnualInflation = 0.03;
        double inflationPrice = (gameReleasePrice * (Math.pow((1+averageAnnualInflation),(year - gameReleaseYear))));
        return Math.round(inflationPrice * 100.0) / 100.0;
    }

    //override toString to print out objects later
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
