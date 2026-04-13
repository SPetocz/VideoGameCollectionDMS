import javax.swing.*;

/**
 * PURPOSE:
 * Serves as the entry point of the application.
 * <p>
 * FUNCTIONALITY:
 * This class initializes the program by creating an instance of the GUI
 * and launching the main application window.
 * <p>
 * ROLE IN SYSTEM:
 * It starts the application and hands control over to the GUI for
 * user interaction.
 * <p>
 * RELATIONSHIPS:
 * Depends on the GUI class to display and manage the user interface.
 */
public class Main {

    /**
     * Starts the application by creating and launching the GUI.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args){
        //Creating a GUI Object
        GUI gui = new GUI();
        //Launching GUI Window
        gui.launch();
    }
}
