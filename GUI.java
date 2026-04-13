import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.Year;

/**
 * PURPOSE:
 * Provides the graphical user interface for the Video Game Collection Database Management System.
 * <p>
 * FUNCTIONALITY:
 * This class builds and manages all GUI components including the main window and dialogs
 * for adding, updating, deleting, and analyzing video game records. It also handles all
 * user interactions such as button clicks, form inputs, and table updates.
 * <p>
 * ROLE IN SYSTEM:
 * Acts as the presentation layer of the application, allowing users to interact with
 * the database visually instead of through direct database queries.
 * <p>
 * RELATIONSHIPS:
 * Uses DBHandler to perform all database operations and VideoGame objects to represent data.
 */
public class GUI {

    //setting up colors to use
    Color bgColor = new Color(255, 103, 103);
    Color tableColor = new Color(255, 255, 255);
    Color gridColor = new Color(0, 0, 0);
    Color dialogColor = new Color(255, 103, 103);

    //setting up fonts to use
    Font titleFont = new Font("American Typewriter", Font.BOLD, 64);
    Font bigButtonFont = new Font("Arial", Font.BOLD, 22);
    Font smallButtonFont = new Font("Arial", Font.BOLD, 11);
    Font textFont  = new Font("Arial", Font.PLAIN, 26);
    Font tableFont = new Font("Arial", Font.PLAIN, 14);
    Font tableHeaderFont = new Font("Arial", Font.BOLD, 18);

    //setting up dimensions to use
    Dimension bigButtonSize = new Dimension(120, 45);
    Dimension smallButtonSize = new Dimension(80, 40);
    Dimension dialogBoxSize = new Dimension(700, 430);
    Dimension smallDialogBoxSize = new Dimension(700, 300);

    //field size for forms
    int fieldSize = 20;

    //constraint object for GridBagLayout
    GridBagConstraints c = new GridBagConstraints();

    //JFrame to reference
    JFrame frame = new JFrame();

    //table model to use in methods
    DefaultTableModel model = new DefaultTableModel();

    //JTable to reference
    JTable table;

    //get current year for forms
    int currentYear = Year.now().getValue();

    //DBHandler object for database functions
    DBHandler db = new DBHandler();

    /**
     * Resets GridBagConstraints to default values.
     * @param c the GridBagConstraints object being reset
     */
    public void resetC(GridBagConstraints c){
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,0,0,0);
        c.ipady = 0;
        c.ipadx = 0;
    }

    /**
     * Sets grid position for GridBagLayout constraints.
     * @param c GridBagConstraints object
     * @param x column position
     * @param y row position
     * @return updated GridBagConstraints
     */
    public GridBagConstraints grid(GridBagConstraints c, int x, int y){
        c.gridx = x;
        c.gridy = y;
        return c;
    }

    /**
     * Launches the main application window and loads the home screen.
     */
    public void launch() {
        frame.setTitle("Video Game Collection DMS");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1100, 700);
        frame.setBackground(bgColor);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(homeScreen(frame));
        frame.setVisible(true);
    }

    /**
     * Creates and configures the JTable used for displaying video game records.
     * @return configured JTable
     */
    public JTable getTable(){
        table = new JTable();
        table.setFont(tableFont);
        table.getTableHeader().setFont(tableHeaderFont);
        table.setShowGrid(true);
        table.setGridColor(gridColor);
        table.setRowHeight(22);
        table.setBackground(tableColor);
        return table;
    }

    /**
     * Reloads the table data from the database and refreshes the UI.
     */
    public void loadTable(){
        table.setModel(db.selectAll());
        table.revalidate();
        table.repaint();
    }

    /**
     * Builds the home screen UI and returns the main container.
     * @param frame main application JFrame
     * @return main container panel
     */
    public Container homeScreen(JFrame frame) {
        resetC(c);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(bgColor);

        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new GridBagLayout());
        grid(c, 0, 0);
        c.weighty = 0.15;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        firstPanel.setBackground(bgColor);
        mainPanel.add(firstPanel, c);
        resetC(c);

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new GridBagLayout());
        secondPanel.setBackground(bgColor);
        secondPanel.add(new JLabel(" "), c);
        grid(c, 0, 1);
        c.weighty = 0.05;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        mainPanel.add(secondPanel, c);
        resetC(c);

        JPanel thirdPanel = new JPanel();
        thirdPanel.setLayout(new GridBagLayout());
        thirdPanel.setBackground(bgColor);
        grid(c, 0, 2);
        c.weighty = 0.6;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 50, 0 ,50);
        mainPanel.add(thirdPanel, c);
        resetC(c);

        JPanel fourthPanel = new JPanel();
        fourthPanel.setLayout(new GridBagLayout());
        fourthPanel.setBackground(bgColor);
        fourthPanel.add(new JLabel(" "), c);
        grid(c, 0, 3);
        c.weighty = 0.2;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        mainPanel.add(fourthPanel, c);
        resetC(c);

        JLabel title = new JLabel("Video Game Collection DMS");
        title.setFont(titleFont);
        title.setBackground(bgColor);
        firstPanel.add(title, c);

        JLabel spacer1 = new JLabel(" ");
        grid(c, 0,0);
        secondPanel.add(spacer1, c);

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(bigButtonSize);
        addButton.setFont(bigButtonFont);
        grid(c, 1, 0);
        secondPanel.add(addButton, c);
        resetC(c);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDialog();
            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(bigButtonSize);
        removeButton.setFont(bigButtonFont);
        grid(c, 2, 0);
        secondPanel.add(removeButton, c);
        resetC(c);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row!=-1){
                    db.deleteEntry(Integer.parseInt(String.valueOf(table.getValueAt(row, 0))));
                    loadTable();
                }
            }
        });

        JButton updateButton = new JButton("Update");
        updateButton.setPreferredSize(bigButtonSize);
        updateButton.setFont(bigButtonFont);
        grid(c, 3, 0);
        secondPanel.add(updateButton, c);
        resetC(c);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow()!=-1){
                    updateDialog();
                }
            }
        });

        JButton inflationButton = new JButton("Inflation");
        inflationButton.setPreferredSize(bigButtonSize);
        inflationButton.setFont(bigButtonFont);
        grid(c, 4, 0);
        secondPanel.add(inflationButton, c);
        resetC(c);

        inflationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow()!=-1){
                    inflationDialog();
                }}
        });

        JLabel spacer2 = new JLabel(" ");
        grid(c, 5, 0);
        secondPanel.add(spacer2, c);
        resetC(c);

        JScrollPane scrollPane = new JScrollPane();
        c.fill = GridBagConstraints.BOTH;
        thirdPanel.add(scrollPane, c);
        table = getTable();
        scrollPane.setViewportView(table);
        if(db.conn!=null) {
            loadTable();
        }
        resetC(c);

        JLabel spacer3 = new JLabel("                    ");
        grid(c, 0, 0);
        fourthPanel.add(spacer3, c);
        resetC(c);

        JButton loadButton = new JButton("Connect To Database");
        loadButton.setSize(bigButtonSize);
        loadButton.setFont(bigButtonFont);
        grid(c, 1, 0);
        c.anchor = GridBagConstraints.CENTER;
        fourthPanel.add(loadButton, c);
        resetC(c);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDialog();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.setSize(bigButtonSize);
        quitButton.setFont(bigButtonFont);
        grid(c, 2, 0);
        fourthPanel.add(quitButton, c);
        resetC(c);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.disconnectDB();
                System.exit(0);
            }
        });

        return mainPanel;
    }

    /**
     * Opens a dialog to select and connect to a database file.
     */
    public void loadDialog(){
        JDialog loadDialog = new JDialog();
        loadDialog.setTitle("Database File Selection");
        loadDialog.setSize(700,500);
        loadDialog.setResizable(false);
        loadDialog.setLocationRelativeTo(null);
        loadDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel dialogPanel = new JPanel();
        dialogPanel.setBackground(dialogColor);
        JFileChooser fileChooser = new JFileChooser();
        dialogPanel.add(fileChooser);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Database Files Only", "db");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setBackground(dialogColor);
        fileChooser.setForeground(dialogColor);
        fileChooser.setOpaque(false);

        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)){
                    File file = fileChooser.getSelectedFile();
                    String filePath = file.toString();
                    db.connectDB(filePath);
                    table.setModel(db.selectAll());
                    loadDialog.dispose();
                } else if(e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)){
                    loadDialog.dispose();
                } else {
                    System.out.println("Error getting file chooser selection.");
                }
            }
        });

        loadDialog.setContentPane(dialogPanel);
        loadDialog.setVisible(true);
    }

    /**
     * Opens a dialog to display inflation-adjusted price of selected game.
     */
    public void inflationDialog(){
        JDialog inflationDialog = new JDialog();
        inflationDialog.setSize(smallDialogBoxSize);
        inflationDialog.setResizable(false);
        inflationDialog.setLocationRelativeTo(null);
        inflationDialog.setTitle("Inflation Calculator");
        inflationDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(dialogColor);
        inflationDialog.setContentPane(panel);

        JLabel dialogTitleLabel = new JLabel("Inflation Calculator");
        dialogTitleLabel.setFont(titleFont);
        panel.add(dialogTitleLabel);

        JLabel spacer = new JLabel(" ");
        grid(c, 0, 1);
        panel.add(spacer, c);
        resetC(c);

        JLabel gameLabel = new JLabel("");
        int row = table.getSelectedRow();
        String title = (String) table.getValueAt(row, 1);
        int year = (int) table.getValueAt(row, 4);
        double price = Double.parseDouble((String) table.getValueAt(row, 5));

        VideoGame videoGame = new VideoGame(0, title, "temp", "temp", year, price, false);
        gameLabel.setText(videoGame.getGameTitle() + "'s Release Price (" + year + "): $" + videoGame.getGameReleasePrice());
        gameLabel.setFont(textFont);
        grid(c, 0, 2);
        panel.add(gameLabel, c);
        resetC(c);

        JLabel inflateLabel = new JLabel(videoGame.getGameTitle() + "'s Inflated Price: $" + videoGame.calculateInflationPrice());
        inflateLabel.setFont(textFont);
        grid(c, 0, 3);
        panel.add(inflateLabel, c);
        resetC(c);

        JButton exitButton = new JButton("Return");
        exitButton.setPreferredSize(bigButtonSize);
        exitButton.setFont(bigButtonFont);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inflationDialog.dispose();
            }
        });

        grid(c, 0, 4);
        panel.add(exitButton, c);
        inflationDialog.setVisible(true);
    }

    /**
     * Opens dialog for adding a new video game entry.
     */
    public void addDialog(){
        JDialog addDialog = new JDialog();
        addDialog.setTitle("Add New Video Game");
        addDialog.setSize(dialogBoxSize);
        addDialog.setLocationRelativeTo(null);
        addDialog.setResizable(false);
        addDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        addDialog.setContentPane(panel);
        panel.setLayout(new GridBagLayout());
        panel.setBackground(dialogColor);

        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        top.setBackground(bgColor);
        grid(c, 0,0);
        panel.add(top);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        middle.setBackground(bgColor);
        grid(c, 0,1);
        panel.add(middle, c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        bottom.setBackground(bgColor);
        grid(c, 0, 2);
        panel.add(bottom, c);
        resetC(c);

        JLabel title = new JLabel("Add New Video Game");
        title.setFont(titleFont);
        title.setBackground(bgColor);
        top.add(title, c);

        JLabel idLabel = new JLabel("Game ID: ");
        idLabel.setFont(textFont);
        idLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel titleLabel = new JLabel("Title: ");
        titleLabel.setFont(textFont);
        titleLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel genreLabel = new JLabel("Genre: ");
        genreLabel.setFont(textFont);
        genreLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel platformLabel = new JLabel("Platform: ");
        platformLabel.setFont(textFont);
        platformLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel yearLabel = new JLabel("Release Year: ");
        yearLabel.setFont(textFont);
        yearLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel priceLabel = new JLabel("Release Price: ");
        priceLabel.setFont(textFont);
        priceLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel multiplayerLabel = new JLabel("Multiplayer: ");
        multiplayerLabel.setFont(textFont);
        multiplayerLabel.setHorizontalAlignment(JLabel.RIGHT);

        JTextField idField = new JTextField(fieldSize);
        JTextField titleField = new JTextField(fieldSize);
        JTextField genreField = new JTextField(fieldSize);
        JTextField platformField = new JTextField(fieldSize);
        JComboBox<Integer> yearBox = new JComboBox<>();
        for(int i = currentYear; i > 1957; i--){
            yearBox.addItem(i);
        }
        yearBox.setSelectedItem(null);
        JTextField priceField = new JTextField(fieldSize);
        JCheckBox multiplayerBox = new JCheckBox();

        grid(c,0,0);
        c.anchor = GridBagConstraints.EAST;
        middle.add(idLabel, c);
        grid(c,1,0);
        middle.add(idField, c);
        grid(c,0,1);
        middle.add(titleLabel, c);
        grid(c,1,1);
        middle.add(titleField, c);
        grid(c,0,2);
        middle.add(genreLabel, c);
        grid(c,1,2);
        middle.add(genreField, c);
        grid(c,0,3);
        middle.add(platformLabel, c);
        grid(c,1,3);
        middle.add(platformField, c);
        grid(c,0,4);
        middle.add(yearLabel, c);
        grid(c,1,4);
        c.anchor = GridBagConstraints.WEST;
        middle.add(yearBox, c);
        grid(c, 0,5);
        c.anchor = GridBagConstraints.EAST;
        middle.add(priceLabel, c);
        grid(c, 1, 5);
        middle.add(priceField, c);
        grid(c, 0, 6);
        middle.add(multiplayerLabel, c);
        grid(c,1,6);
        c.anchor = GridBagConstraints.WEST;
        middle.add(multiplayerBox, c);
        resetC(c);

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setFont(textFont);
        grid(c, 1, 0);
        c.insets = new Insets(0,0,5,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        bottom.add(errorLabel, c);

        resetC(c);

        JPanel bottomFirst = new JPanel();
        bottomFirst.setLayout(new GridBagLayout());
        bottomFirst.setBackground(bgColor);
        JPanel bottomSecond = new JPanel();
        bottomSecond.setLayout(new GridBagLayout());
        bottomSecond.setBackground(bgColor);
        JPanel bottomThird = new JPanel();
        bottomThird.setLayout(new GridBagLayout());
        bottomThird.setBackground(bgColor);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(bgColor);

        resetC(c);
        c.fill = GridBagConstraints.HORIZONTAL;
        grid(c,0,3);
        panel.add(buttonPanel, c);
        resetC(c);

        grid(c, 0,0);
        c.anchor = GridBagConstraints.EAST;
        buttonPanel.add(bottomFirst, c);

        grid(c,1,0);
        c.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(bottomSecond, c);

        grid(c, 2, 0);
        c.anchor = GridBagConstraints.WEST;
        buttonPanel.add(bottomThird, c);

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(smallButtonSize);
        clearButton.setFont(smallButtonFont);
        bottomFirst.add(clearButton, c);
        resetC(c);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                titleField.setText("");
                genreField.setText("");
                platformField.setText("");
                yearBox.setSelectedItem(null);
                priceField.setText("");
                multiplayerBox.setSelected(false);
                errorLabel.setText(" ");
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(smallButtonSize);
        submitButton.setFont(smallButtonFont);
        bottomSecond.add(submitButton, c);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean checked = false;

                if(db.conn == null){
                    errorLabel.setText("Not Connected to Database.");
                }else if(idField.getText().isBlank()){
                    errorLabel.setText("ID field cannot be blank.");
                }else if(!idField.getText().matches("\\d+")){
                    errorLabel.setText("ID field must only contain numbers.");
                }else if(db.duplicateEntry(Integer.parseInt(idField.getText()))){
                    errorLabel.setText("ID already exists in collection.");
                }else if(titleField.getText().isBlank()){
                    errorLabel.setText("Title field cannot be blank.");
                }else if(genreField.getText().isBlank()){
                    errorLabel.setText("Genre field cannot be blank.");
                }else if(platformField.getText().isBlank()){
                    errorLabel.setText("Platform field cannot be blank.");
                }else if(yearBox.getSelectedItem()==null){
                    errorLabel.setText("Must select release year.");
                }else if(priceField.getText().isBlank()){
                    errorLabel.setText("Price field cannot be blank.");
                }else if(!priceField.getText().matches("\\d+(\\.\\d{2})?")){
                    errorLabel.setText("Invalid price.");
                }else{
                    checked = true;
                }

                if(checked){
                    int gameID = Integer.parseInt(idField.getText());
                    String gameTitle = titleField.getText();
                    String gameGenre = genreField.getText();
                    String gamePlatform = platformField.getText();
                    int gameYear = Integer.parseInt(yearBox.getSelectedItem().toString());
                    double gamePrice = Double.parseDouble(priceField.getText());
                    boolean gameMultiplayer = multiplayerBox.isSelected();

                    db.insertEntry(new VideoGame(
                            gameID,
                            gameTitle,
                            gameGenre,
                            gamePlatform,
                            gameYear,
                            gamePrice,
                            gameMultiplayer
                    ));

                    loadTable();
                    addDialog.dispose();
                }
            }
        });

        JButton returnButton = new JButton("Return");
        returnButton.setPreferredSize(smallButtonSize);
        returnButton.setFont(smallButtonFont);
        bottomThird.add(returnButton, c);

        resetC(c);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDialog.dispose();
            }
        });

        addDialog.setVisible(true);
    }

    /**
     * Opens dialog for updating an existing video game entry.
     */
    public void updateDialog(){
        JDialog updateDialog = new JDialog();
        updateDialog.setTitle("Update Video Game");
        updateDialog.setSize(dialogBoxSize);
        updateDialog.setLocationRelativeTo(null);
        updateDialog.setResizable(false);
        updateDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        updateDialog.setContentPane(panel);
        panel.setLayout(new GridBagLayout());
        panel.setBackground(dialogColor);

        int row = table.getSelectedRow();
        int id = Integer.parseInt(table.getValueAt(row, 0).toString());
        String gameTitle = (String) table.getValueAt(row, 1);
        String genre = (String) table.getValueAt(row, 2);
        String platform = (String) table.getValueAt(row, 3);
        int year = (int) table.getValueAt(row, 4);
        double price = Double.parseDouble(String.valueOf(table.getValueAt(row, 5)));
        boolean multiplayer = (boolean) table.getValueAt(row, 6);

        VideoGame game = new VideoGame(id, gameTitle, genre, platform, year, price, multiplayer);

        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        top.setBackground(bgColor);
        grid(c, 0,0);
        panel.add(top);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        middle.setBackground(bgColor);
        grid(c, 0,1);
        panel.add(middle, c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        bottom.setBackground(bgColor);
        grid(c, 0, 2);
        panel.add(bottom, c);
        resetC(c);

        JLabel title = new JLabel("Update Video Game");
        title.setFont(titleFont);
        title.setBackground(bgColor);
        top.add(title, c);

        JLabel idLabel = new JLabel("Game ID: ");
        idLabel.setFont(textFont);
        idLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel titleLabel = new JLabel("Title: ");
        titleLabel.setFont(textFont);
        titleLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel genreLabel = new JLabel("Genre: ");
        genreLabel.setFont(textFont);
        genreLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel platformLabel = new JLabel("Platform: ");
        platformLabel.setFont(textFont);
        platformLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel yearLabel = new JLabel("Release Year: ");
        yearLabel.setFont(textFont);
        yearLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel priceLabel = new JLabel("Release Price: ");
        priceLabel.setFont(textFont);
        priceLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel multiplayerLabel = new JLabel("Multiplayer: ");
        multiplayerLabel.setFont(textFont);
        multiplayerLabel.setHorizontalAlignment(JLabel.RIGHT);

        JTextField idField = new JTextField(fieldSize);
        JTextField titleField = new JTextField(fieldSize);
        JTextField genreField = new JTextField(fieldSize);
        JTextField platformField = new JTextField(fieldSize);
        JComboBox<Integer> yearBox = new JComboBox<>();
        for(int i = currentYear; i > 1957; i--){
            yearBox.addItem(i);
        }
        yearBox.setSelectedItem(null);
        JTextField priceField = new JTextField(fieldSize);
        JCheckBox multiplayerBox = new JCheckBox();

        grid(c,0,0);
        c.anchor = GridBagConstraints.EAST;
        middle.add(idLabel, c);
        grid(c,1,0);
        middle.add(idField, c);
        grid(c,0,1);
        middle.add(titleLabel, c);
        grid(c,1,1);
        middle.add(titleField, c);
        grid(c,0,2);
        middle.add(genreLabel, c);
        grid(c,1,2);
        middle.add(genreField, c);
        grid(c,0,3);
        middle.add(platformLabel, c);
        grid(c,1,3);
        middle.add(platformField, c);
        grid(c,0,4);
        middle.add(yearLabel, c);
        grid(c,1,4);
        c.anchor = GridBagConstraints.WEST;
        middle.add(yearBox, c);
        grid(c, 0,5);
        c.anchor = GridBagConstraints.EAST;
        middle.add(priceLabel, c);
        grid(c, 1, 5);
        middle.add(priceField, c);
        grid(c, 0, 6);
        middle.add(multiplayerLabel, c);
        grid(c,1,6);
        c.anchor = GridBagConstraints.WEST;
        middle.add(multiplayerBox, c);
        resetC(c);

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setFont(textFont);
        grid(c, 1, 0);
        c.insets = new Insets(0,0,5,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        bottom.add(errorLabel, c);

        resetC(c);

        JPanel bottomFirst = new JPanel();
        bottomFirst.setLayout(new GridBagLayout());
        bottomFirst.setBackground(bgColor);
        JPanel bottomSecond = new JPanel();
        bottomSecond.setLayout(new GridBagLayout());
        bottomSecond.setBackground(bgColor);
        JPanel bottomThird = new JPanel();
        bottomThird.setLayout(new GridBagLayout());
        bottomThird.setBackground(bgColor);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(bgColor);

        resetC(c);
        c.fill = GridBagConstraints.HORIZONTAL;
        grid(c,0,3);
        panel.add(buttonPanel, c);
        resetC(c);

        grid(c, 0,0);
        c.anchor = GridBagConstraints.EAST;
        buttonPanel.add(bottomFirst, c);

        grid(c,1,0);
        c.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(bottomSecond, c);

        grid(c, 2, 0);
        c.anchor = GridBagConstraints.WEST;
        buttonPanel.add(bottomThird, c);

        idField.setText(String.valueOf(game.getGameID()));
        titleField.setText(game.getGameTitle());
        genreField.setText(game.getGameGenre());
        platformField.setText(game.getGamePlatform());
        yearBox.setSelectedItem(game.getGameReleaseYear());
        priceField.setText(String.valueOf(game.getGameReleasePrice()));
        multiplayerBox.setSelected(game.isMultiplayer());

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(smallButtonSize);
        clearButton.setFont(smallButtonFont);
        bottomFirst.add(clearButton, c);

        resetC(c);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText(String.valueOf(game.getGameID()));
                titleField.setText(game.getGameTitle());
                genreField.setText(game.getGameGenre());
                platformField.setText(game.getGamePlatform());
                yearBox.setSelectedItem(game.getGameReleaseYear());
                priceField.setText(String.valueOf(game.getGameReleasePrice()));
                multiplayerBox.setSelected(game.isMultiplayer());
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(smallButtonSize);
        submitButton.setFont(smallButtonFont);
        bottomSecond.add(submitButton, c);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.deleteEntry(id);

                boolean checked = false;

                if(idField.getText().isBlank()){
                    errorLabel.setText("ID field cannot be blank.");
                }else if(!idField.getText().matches("\\d+")){
                    errorLabel.setText("ID field must only contain numbers.");
                }else if(db.duplicateEntry(id)){
                    errorLabel.setText("ID already exists in collection.");
                }else if(titleField.getText().isBlank()){
                    errorLabel.setText("Title field cannot be blank.");
                }else if(genreField.getText().isBlank()){
                    errorLabel.setText("Genre field cannot be blank.");
                }else if(platformField.getText().isBlank()){
                    errorLabel.setText("Platform field cannot be blank.");
                }else if(yearBox.getSelectedItem()==null){
                    errorLabel.setText("Must select release year.");
                }else if(priceField.getText().isBlank()){
                    errorLabel.setText("Price field cannot be blank.");
                }else if(!priceField.getText().matches("\\d+(\\.\\d{2})?")){
                    errorLabel.setText("Invalid price.");
                }else{
                    checked = true;
                }

                if(checked){
                    int gameID = Integer.parseInt(idField.getText());
                    String gameTitle = titleField.getText();
                    String gameGenre = genreField.getText();
                    String gamePlatform = platformField.getText();
                    int gameYear = Integer.parseInt(yearBox.getSelectedItem().toString());
                    double gamePrice = Double.parseDouble(priceField.getText());
                    boolean gameMultiplayer = multiplayerBox.isSelected();

                    db.insertEntry(new VideoGame(
                            gameID,
                            gameTitle,
                            gameGenre,
                            gamePlatform,
                            gameYear,
                            gamePrice,
                            gameMultiplayer
                    ));

                    loadTable();
                    updateDialog.dispose();
                }
            }
        });

        JButton returnButton = new JButton("Return");
        returnButton.setPreferredSize(smallButtonSize);
        returnButton.setFont(smallButtonFont);
        bottomThird.add(returnButton, c);

        resetC(c);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.deleteEntry(id);
                db.insertEntry(game);
                updateDialog.dispose();
            }
        });

        updateDialog.setVisible(true);
    }
}