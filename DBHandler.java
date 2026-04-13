import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * PURPOSE:
 * Handles all database operations for the application.
 * <p>
 * FUNCTIONALITY:
 * This class manages the connection to an SQLite database and performs
 * CRUD operations (Create, Read, Update, Delete) for VideoGame records.
 * It provides methods to connect/disconnect from the database, insert
 * new entries, delete entries, retrieve all data, and check for duplicates.
 * <p>
 * ROLE IN SYSTEM:
 * Acts as the data persistence layer of the application, allowing the GUI
 * and other components to interact with the database.
 * <p>
 * RELATIONSHIPS:
 * Works with the VideoGame class to store and retrieve game objects.
 * Supplies data to the GUI via DefaultTableModel for display in tables.
 */
public class DBHandler {

    String url;
    Connection conn;

    /**
     * Creates a new DBHandler instance.
     *
     * The database connection is not established until connectDB() is called.
     */
    public DBHandler() {
        // no initialization required
    }

    /**
     * Connects to the SQLite database using the provided file path.
     * <p>
     * This method handles SQLExceptions internally if a connection error occurs.
     *
     * @param dbFilePath The file path to the .db database file.
     *
     */
    public void connectDB(String dbFilePath){
        url = "jdbc:sqlite:" + dbFilePath;
        try{
            conn = DriverManager.getConnection(url);
            if(conn != null){
                System.out.println("Connected to DB.");
            }
        }catch(SQLException e){
            System.err.println("Connection Error: " + e.getMessage());
        }
    }

    /**
     * Closes the connection to the database if it is currently open.
     *<p>
     * This method handles SQLExceptions internally if a connection error occurs.
     * This method handles SQLExceptions internally if an error occurs while closing.
     */
    public void disconnectDB(){
        try{
            if(conn != null){
                conn.close();
                System.out.println("Disconnected from DB.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all video game records from the database and returns them
     * in a non-editable table model.
     *<p>
     * This method handles SQLExceptions internally if a connection error occurs.
     * @return A DefaultTableModel containing all game records.
     *
     */
    public DefaultTableModel selectAll(){

        String selectAll = "SELECT * FROM Games";
        String[] columns = {"ID", "Title", "Genre", "Platform", "Release Year", "Release Price", "Multiplayer"};

        DefaultTableModel model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        try(Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(selectAll)){

            while(rs.next()){
                Object[] row = {
                        rs.getInt("gameID"),
                        rs.getString("gameTitle"),
                        rs.getString("gameGenre"),
                        rs.getString("gamePlatform"),
                        rs.getInt("gameReleaseYear"),
                        String.format("%.2f", rs.getFloat("gameReleasePrice")),
                        rs.getBoolean("multiplayer")
                };
                model.addRow(row);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return model;
    }

    /**
     * Deletes a video game record from the database using its ID.
     *<p>
     * This method handles SQLExceptions internally if a connection error occurs.
     * @param gameID The unique identifier of the game to delete.
     *
     */
    public void deleteEntry(int gameID) {
        String delete = "DELETE FROM Games WHERE gameID = " + gameID;

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(delete);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Inserts a new VideoGame record into the database.
     *<p>
     * This method handles SQLExceptions internally if a connection error occurs.
     * @param game The VideoGame object containing data to insert.
     *
     */
    public void insertEntry(VideoGame game) {

        String insert = "INSERT INTO Games (gameID, gameTitle, gameGenre, gamePlatform, gameReleaseYear, gameReleasePrice, multiplayer) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, game.getGameID());
            ps.setString(2, game.getGameTitle());
            ps.setString(3, game.getGameGenre());
            ps.setString(4, game.getGamePlatform());
            ps.setInt(5, game.getGameReleaseYear());
            ps.setDouble(6, game.getGameReleasePrice());
            ps.setBoolean(7, game.isMultiplayer());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether a game with the given ID already exists in the database.
     *<p>
     * This method handles SQLExceptions internally if a connection error occurs.
     * @param gameID The ID to check for duplicates.
     * @return true if the game already exists, false otherwise.
     *
     */
    public boolean duplicateEntry(int gameID){
        String checkID = "SELECT * FROM Games WHERE gameID = " + gameID;

        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(checkID);

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}