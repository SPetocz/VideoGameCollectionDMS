import javax.swing.table.DefaultTableModel;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {

    //url string to store connection to db
    String url;
    //connection object for db functions
    Connection conn;

    //method that takes in a filepath to a .db and connects to the db
    public  void connectDB(String dbFilePath){
        url = "jdbc:sqlite:" + dbFilePath;
        try{
            conn = DriverManager.getConnection(url);
            if(conn!=null){
                System.out.println("Connected to DB.");
            }
        }catch(SQLException e){
            System.err.println("Connection Error: " + e.getMessage());
        }
    }

    //method that disconnects from the db
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

    //method to select all from games table in db
    public DefaultTableModel selectAll(){

        String selectAll = "SELECT * FROM Games";
        String [] columns = {"ID", "Title", "Genre", "Platform", "Release Year", "Release Price", "Multiplayer"};
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

    //method that deletes entry from games using gameID
    public void deleteEntry(int gameID) {
        String delete = "DELETE FROM Games WHERE gameID = " + String.valueOf(gameID);
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(delete);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //method that takes in a game object and inserts it into the games table in the db
    public void insertEntry(VideoGame game) {
        int id = game.getGameID();
        String title = game.getGameTitle();
        String genre = game.getGameGenre();
        String platform = game.getGamePlatform();
        int year = game.getGameReleaseYear();
        double price = game.getGameReleasePrice();
        boolean multiplayer = game.isMultiplayer();
        String insert = "INSERT INTO Games (gameID, gameTitle, gameGenre, gamePlatform, gameReleaseYear, gameReleasePrice, multiplayer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, genre);
            ps.setString(4, platform);
            ps.setInt(5, year);
            ps.setDouble(6, price);
            ps.setBoolean(7, multiplayer);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //method that takes in a gameID and queries the games table to see if it is already present
    public boolean duplicateEntry(int gameID){
        String checkID = "SELECT * FROM Games WHERE gameID = " + String.valueOf(gameID);
        ResultSet rs = null;
        try{
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(checkID);
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
