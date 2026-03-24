import java.nio.file.Path;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class GameCollectionTest {

    GameCollection collection;
    VideoGame game1;
    VideoGame game2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        //creating dummy objects to test with
        collection = new GameCollection();
        //game 1 contains reasonable data
        game1 = new VideoGame(
                1,
                "Test_Game",
                "Test_Genre",
                "Test_Platform",
                2000,
                19.99,
                false);
        //game 2 contains some unreasonable data
        game2 = new VideoGame(
                -1,
                "Bad_Game",
                "Test_Genre",
                "Test_Platform",
                1946,
                -5.00,
                true
        );
    }

    @org.junit.jupiter.api.Test
    void addGame() {
        //checking if we can add a game to a collection
        assertEquals(true, collection.addGame(game1), "Error. Game Could not be added.");
        //assertEquals(true, collection.addGame(game2), "Error. Game Could not be added.");
    }

    @org.junit.jupiter.api.Test
    void deleteGameByID() {
        //checking if we can delete a game from collection using gameID as accessor
        collection.addGame(game1);
        assertEquals(true, collection.deleteGameByID(game1.getGameID()), "Error. Game Could not be removed by gameID.");
        //assertEquals(true, collection.deleteGameByID(game1.getGameID()), "Error. Game Could not be removed by gameID.");
    }

    @org.junit.jupiter.api.Test
    void deleteGameByTitle() {
        //checking if we can delete a game from collection using gameTitle as accessor
        collection.addGame(game1);
        assertEquals(true, collection.deleteGameByTitle(game1.getGameTitle()), "Error. Game Could not be removed by gameTitle.");
        //assertEquals(true, collection.deleteGameByTitle(game1.getGameTitle()), "Error. Game Could not be removed by gameTitle.");
    }

    @org.junit.jupiter.api.Test
    void updateGameIDByTitle() {
        //checking if we can update a gameID using gameTitle as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGameIDByTitle(game1.getGameTitle(), 2), "Error. Game Could not update gameID by gameTitle.");
        //assertEquals(true, collection.updateGameIDByTitle(game1.getGameTitle(), -1), "Error. Game Could not update gameID by gameTitle.");
    }

    @org.junit.jupiter.api.Test
    void updateGameTitleByTitle() {
        //checking if we can update a gameTitle using gameTitle as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGameTitleByTitle(game1.getGameTitle(), "New_Title"), "Error. Game Could not update gameTitle by gameTitle.");
        //assertEquals(true, collection.updateGameTitleByTitle(game1.getGameTitle(), ""), "Error. Game Could not update gameTitle by gameTitle.");
    }

    @org.junit.jupiter.api.Test
    void updateGameGenreByTitle() {
        //checking if we can update a gameGenre using gameTitle as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGameGenreByTitle(game1.getGameTitle(), "New_Genre"), "Error. Game Could not update gameGenre by gameTitle.");
        //assertEquals(true, collection.updateGameGenreByTitle(game1.getGameTitle(), ""), "Error. Game Could not update gameGenre by gameTitle.");
    }

    @org.junit.jupiter.api.Test
    void updateGamePlatformByTitle() {
        //checking if we can update gamePlatform using gameTitle as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGamePlatformByTitle(game1.getGameTitle(), "New_Platform"), "Error. Game Could not update gamePlatform by gameTitle.");
        //assertEquals(true, collection.updateGamePlatformByTitle(game1.getGameTitle(), ""), "Error. Game Could not update gamePlatform by gameTitle.");

    }

    @org.junit.jupiter.api.Test
    void updateGameYearByTitle() {
        //checking if we can update gameYear using gameTitle as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGameYearByTitle(game1.getGameTitle(), 2005), "Error. Game Could not update gameYear by gameTitle.");
        //assertEquals(true, collection.updateGameYearByTitle(game1.getGameTitle(), 1946), "Error. Game Could not update gameYear by gameTitle.");

    }

    @org.junit.jupiter.api.Test
    void updateGamePriceByTitle() {
        //checking if we can update gamePrice using gameTitle as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGamePriceByTitle(game1.getGameTitle(), 25.99), "Error. Game Could not update gamePrice by gameTitle.");
        //assertEquals(true, collection.updateGamePriceByTitle(game1.getGameTitle(), -10.99), "Error. Game Could not update gamePrice by gameTitle.");

    }

    @org.junit.jupiter.api.Test
    void updateGameMultiplayerByTitle() {
        //checking if we can update gameMultiplayer using gameTitle as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGameMultiplayerByTitle(game1.getGameTitle(), true), "Error. Game Could not update gameMultiplayer by gameTitle.");
        //assertEquals(true, collection.updateGameMultiplayerByTitle(game2.getGameTitle(), true), "Error. Game Could not update gameMultiplayer by gameTitle.");

    }

    @org.junit.jupiter.api.Test
    void updateGameIDByID() {
        //checking if we can update gameID using gameID as accessor
        collection.addGame(game1);
        collection.addGame(game2);
        assertEquals(true, collection.updateGameIDByID(game1.getGameID(), 2), "Error. Game Could not update gameID by gameID.");
        //assertEquals(true, collection.updateGameIDByID(game1.getGameID(), -3), "Error. Game Could not update gameID by gameID.");

    }

    @org.junit.jupiter.api.Test
    void updateGameTitleByID() {
        //checking if we can update gameTitle using gameID as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGameTitleByID(game1.getGameID(), "New_Title"), "Error. Game Could not update gameTitle by gameID.");
        //assertEquals(true, collection.updateGameTitleByID(game1.getGameID(), ""), "Error. Game Could not update gameTitle by gameID.");

    }

    @org.junit.jupiter.api.Test
    void updateGameGenreByID() {
        //checking if we can update gameGenre using gameID as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGameGenreByID(game1.getGameID(), "New_Genre"), "Error. Game Could not update gameGenre by gameID.");
        //assertEquals(true, collection.updateGameGenreByID(game1.getGameID(), ""), "Error. Game Could not update gameGenre by gameID.");

    }

    @org.junit.jupiter.api.Test
    void updateGamePlatformByID() {
        //checking if we can update gamePlatform using gameID as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGamePlatformByID(game1.getGameID(), "New_Platform"), "Error. Game Could not update gamePlatform by gameID.");
        //assertEquals(true, collection.updateGamePlatformByID(game1.getGameID(), ""), "Error. Game Could not update gamePlatform by gameID.");

    }

    @org.junit.jupiter.api.Test
    void updateGameYearByID() {
        //checking if we can update gameYear using gameID as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGameYearByID(game1.getGameID(), 2005), "Error. Game Could not update gameYear by gameID.");
        //assertEquals(true, collection.updateGameYearByID(game1.getGameID(), 2209), "Error. Game Could not update gameYear by gameID.");

    }

    @org.junit.jupiter.api.Test
    void updateGamePriceByID() {
        //checking if we can update gamePrice using gameID as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGamePriceByID(game1.getGameID(), 25.99), "Error. Game Could not update gamePrice by gameID.");
        //assertEquals(true, collection.updateGamePriceByID(game1.getGameID(), -39.46), "Error. Game Could not update gamePrice by gameID.");

    }

    @org.junit.jupiter.api.Test
    void updateGameMultiplayerByID() {
        //checking if we can update gameMultiplayer using gameID as accessor
        collection.addGame(game1);
        assertEquals(true, collection.updateGameMultiplayerByID(game1.getGameID(), true), "Error. Game Could not update gameMultiplayer by gameID.");
        //assertEquals(true, collection.updateGameMultiplayerByID(game2.getGameID(), true), "Error. Game Could not update gameMultiplayer by gameID.");

    }

    @org.junit.jupiter.api.Test
    void calculateInflationPrice() {
        //checking to see if inflation price can be calculated correctly
        int year = Year.now().getValue();
        double averageAnnualInflation = 0.03;
        double game1InflationPrice = (game1.getGameReleasePrice() * (Math.pow((1+averageAnnualInflation),(year - game1.getGameReleaseYear()))));
        double game1Result = Math.round(game1InflationPrice * 100.0) / 100.0;
        double game2InflationPrice = (game2.getGameReleasePrice() * (Math.pow((1+averageAnnualInflation),(year - game2.getGameReleaseYear()))));
        double game2Result = Math.round(game2InflationPrice * 100.0) / 100.0;

        //game 1 passes inflation test
        assertEquals(game1Result, game1.calculateInflationPrice());
        //game 2 fails inflation test due to negative price
        //assertEquals(game2Result, game2.calculateInflationPrice());
    }

    @org.junit.jupiter.api.Test
    void loadFile() {
        //checks to see if a file loader can read and import objects from .txt file
        List<String> lines = new ArrayList<>();
        String content1 = "1-Stardew Valley-Farming-PC-2016-14.99-true\n2-Minecraft-Sandbox-PC-2011-26.95-true";
        String file1 = "testfile1.txt";
        try {
            // Write the string content to the file, creating the file if it doesn't exist
            Files.writeString(Path.of(file1), content1);
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.getMessage();
        }
        //complete and correct tokens in .txt lines allows for proper object importing
        assertEquals(2, collection.fileLoader(file1, lines, collection), "Error. Could not open and read file correctly.");
        String content2 = "1-Stardew Valley-Farming-PC-14.99-true\n2-Minecraft-Sandbox-PC-2011-26.95-true";
        String file2 = "testfile2.txt";
        try {
            // Write the string content to the file, creating the file if it doesn't exist
            Files.writeString(Path.of(file2), content2);
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.getMessage();
        }
        //missing tokens from .txt file lines inhibits proper object importing
        //assertEquals(2, collection.fileLoader(file2, lines, collection), "Error. Could not open and read file correctly.");

    }

}