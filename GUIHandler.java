import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class GUIHandler {

    //setting up some reusable fonts / colors / dimensions / collection and constraint objects
    GameCollection collection = new GameCollection();
    GridBagConstraints c = new GridBagConstraints();
    Font textFont = new Font("Arial", Font.PLAIN, 24);
    Font buttonFont = new Font("Arial", Font.BOLD, 22);
    Font headerFont = new Font("American Typewriter", Font.BOLD, 56);
    Dimension buttonDimension = new Dimension(250, 80);
    Color bgColor = new Color(141, 230, 221);

    //method to launch application window, and set to homescreen
    public void launch(){
        JFrame frame = new JFrame();
        drawFrame(frame);
        swapScreen(frame, homeScreen(frame));
        frame.setVisible(true);
    }

    //method to reset GridBagConstraints automatically
    public void resetConstraints(GridBagConstraints c){
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weighty = 0;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0,0,0,0);
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 0;
        c.ipady = 0;
    }

    //method to draw application window in customized manner
    public void drawFrame(JFrame frame){
        frame.setTitle("Video Game DMS Project");
        frame.setSize(1100, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(bgColor);
        }

    //method to set focus to specific window
    public void swapScreen(JFrame frame, Container newPanel){
        frame.setContentPane(newPanel);
        frame.revalidate();
        frame.repaint();
    }

    //method to make a table from the arraylist of games
    public JScrollPane getTable(ArrayList<VideoGame> games){
        String [] columnNames = {
                "ID",
                "Title",
                "Genre",
                "Platform",
                "Release Year",
                "Release Price",
                "Multiplayer"
        };

        Object[][] tableGames = new Object[games.size()][7];
        for(int i = 0; i < games.size(); i++){
            tableGames[i][0] = games.get(i).getGameID();
            tableGames[i][1] = games.get(i).getGameTitle();
            tableGames[i][2] = games.get(i).getGameGenre();
            tableGames[i][3] = games.get(i).getGamePlatform();
            tableGames[i][4] = games.get(i).getGameReleaseYear();
            tableGames[i][5] = games.get(i).getGameReleasePrice();
            tableGames[i][6] = games.get(i).isMultiplayer();
        }

        JTable table = new JTable(tableGames, columnNames);
        for(int c = 0; c < table.getColumnCount(); c++){
        table.setDefaultEditor(table.getColumnClass(c), null);
        }
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));

        return new JScrollPane(table);
    }

    //method to create and handle home screen gui
    public Container homeScreen(JFrame frame){
        JPanel homeScreen = new JPanel();
        homeScreen.setBackground(bgColor);
        homeScreen.setLayout(new GridBagLayout());
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel homeScreenLabel = new JLabel("Video Game DMS");
        homeScreenLabel.setFont(headerFont);
        homeScreenLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_END;
        top.setBackground(bgColor);
        top.add(homeScreenLabel, c);
        resetConstraints(c);
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.3;
        homeScreen.add(top, c);
        resetConstraints(c);
        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());
        JButton viewButton = new JButton("View Collection");
        viewButton.setPreferredSize(new Dimension(250, 80));
        viewButton.setFont(buttonFont);
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, viewScreen(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        center.add(viewButton, c);
        JButton addButton = new JButton("Add Game");
        addButton.setPreferredSize(new Dimension(250, 80));
        addButton.setFont(buttonFont);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, addScreen(frame));
            }
        });
        c.gridx = 1;
        c.gridy = 0;
        center.add(addButton, c);
        JButton updateButton = new JButton("Update Game");
        updateButton.setPreferredSize(new Dimension(250, 80));
        updateButton.setFont(buttonFont);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, updateScreen(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        center.add(updateButton, c);
        JButton removeButton = new JButton("Remove Game");
        removeButton.setPreferredSize(new Dimension(250, 80));
        removeButton.setFont(buttonFont);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, removeScreen(frame));
            }
        });
        c.gridx = 1;
        c.gridy = 1;
        center.add(removeButton, c);
        JButton inflationButton = new JButton("Calculate Inflation");
        inflationButton.setPreferredSize(new Dimension(250, 80));
        inflationButton.setFont(buttonFont);
        inflationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, calcScreen(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        center.add(inflationButton, c);
        JButton loadButton = new JButton("Load File");
        loadButton.setPreferredSize(new Dimension(250, 80));
        loadButton.setFont(buttonFont);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, loadScreen(frame));
            }
        });
        c.gridx = 1;
        c.gridy = 2;
        center.add(loadButton, c);
        JButton aboutButton = new JButton("About");
        aboutButton.setPreferredSize(new Dimension(250, 80));
        aboutButton.setFont(buttonFont);
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, aboutScreen(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        center.add(aboutButton, c);
        JButton quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(250, 80));
        quitButton.setFont(buttonFont);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        c.gridx = 1;
        c.gridy = 3;
        center.setBackground(bgColor);
        center.add(quitButton, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.50;
        c.weightx = 0;
        homeScreen.add(center, c);
        resetConstraints(c);
        JPanel bottom = new JPanel();
        JLabel footer = new JLabel("Project Created by Steven Petocz");
        footer.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        c.gridx = 0;
        c.gridy = 0;
        bottom.add(footer, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.2;
        bottom.setBackground(bgColor);
        homeScreen.add(bottom, c);
        resetConstraints(c);
        return homeScreen;
    }

    //method to create and handle video game collection viewing screen gui
    public Container viewScreen(JFrame frame){
            c = new GridBagConstraints();
            JPanel viewPanel = new JPanel();
            viewPanel.setBackground(bgColor);
            viewPanel.setLayout(new GridBagLayout());
            JPanel top = new JPanel();
            top.setBackground(bgColor);
            top.setLayout(new GridBagLayout());
            JLabel label = new JLabel("View Collection");
            label.setFont(new Font("American Typewriter", Font.BOLD, 40));
            top.add(label);
            c.weighty = 0.15;
            viewPanel.add(top, c);
            resetConstraints(c);
            JPanel middle = new JPanel();
            middle.setLayout(new GridBagLayout());

            JScrollPane pane = getTable(collection.games);
            c.weighty = 0.7;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(3,10,10,10);
            middle.add(pane, c);

            c.gridy = 1;
            c.weighty = 0.7;
            viewPanel.add(middle, c);
            resetConstraints(c);
            JPanel bottom = new JPanel();
            bottom.setLayout(new GridBagLayout());
            JButton returnButton = new JButton("Return");
            returnButton.setFont(buttonFont);
            returnButton.setPreferredSize(buttonDimension);
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    swapScreen(frame, homeScreen(frame));
                }
            });
            c.ipady = 2;
            c.insets = new Insets(0,0,20,0);
            bottom.add(returnButton, c);
            c.gridy = 2;
            c.weighty = 0.1;
            viewPanel.add(bottom, c);
            resetConstraints(c);
            middle.setBackground(bgColor);
            bottom.setBackground(bgColor);
            return viewPanel;
    }


    //method to create and handle add video game screen gui
    public Container addScreen(JFrame frame){
        JPanel addPanel = new JPanel(new GridBagLayout());

        // ===================== TOP =====================
        JPanel top = new JPanel(new GridBagLayout());
        JLabel title = new JLabel("Add Game");
        title.setFont(new Font("American Typewriter", Font.BOLD, 40));
        top.add(title);

        c.gridy = 0;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.PAGE_END;
        addPanel.add(top, c);

        // ===================== MIDDLE FORM =====================
        JPanel middle = new JPanel(new GridBagLayout());
        middle.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints form = new GridBagConstraints();
        form.insets = new Insets(10, 10, 10, 10);

        String[] labels = {
                "Game ID:", "Game Title:", "Genre:",
                "Platform:", "Release Year:", "Price:", "Multiplayer:"
        };

        JTextField idField = new JTextField(20);
        JTextField titleField = new JTextField(20);
        JTextField genreField = new JTextField(20);
        JTextField platformField = new JTextField(20);

        // ID digits only
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d+")) super.insertString(fb, offset, string, attr);
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d+")) super.replace(fb, offset, length, text, attrs);
            }
        });
        idField.setDocument(doc);

        // Year combo box
        int currentYear = java.time.Year.now().getValue();
        Integer[] years = new Integer[currentYear - 1958 + 1];
        for (int i = 0; i < years.length; i++) years[i] = 1958 + i;
        JComboBox<Integer> yearBox = new JComboBox<>(years);
        yearBox.setSelectedItem(currentYear);

        // Price formatted
        NumberFormat priceFormat = NumberFormat.getNumberInstance();
        priceFormat.setMinimumFractionDigits(2);
        priceFormat.setMaximumFractionDigits(2);
        priceFormat.setGroupingUsed(false);
        JFormattedTextField priceField = new JFormattedTextField(priceFormat);
        priceField.setColumns(20);
        priceField.setValue(0.0);

        // Multiplayer checkbox
        JCheckBox multiplayerBox = new JCheckBox("Multiplayer");

        JComponent[] inputs = {idField, titleField, genreField, platformField, yearBox, priceField, multiplayerBox};

        for (int i = 0; i < labels.length; i++) {
            form.gridx = 0;
            form.gridy = i;
            form.anchor = GridBagConstraints.EAST;
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(new Font("Arial", Font.BOLD, 24));
            middle.add(lbl, form);

            form.gridx = 1;
            form.anchor = GridBagConstraints.WEST;
            inputs[i].setPreferredSize(new Dimension(250, 30));
            middle.add(inputs[i], form);
        }

        // ===================== BUTTONS =====================
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton clearButton = new JButton("Clear");
        JButton submitButton = new JButton("Add Game");

        clearButton.setFont(buttonFont);
        clearButton.setPreferredSize(buttonDimension);
        submitButton.setFont(buttonFont);
        submitButton.setPreferredSize(buttonDimension);

        buttonPanel.add(clearButton);
        buttonPanel.add(submitButton);

        form.gridx = 0;
        form.gridy = labels.length;
        form.gridwidth = 2;
        form.anchor = GridBagConstraints.CENTER;
        middle.add(buttonPanel, form);

        // ------------------ Clear functionality ------------------
        clearButton.addActionListener(e -> {
            swapScreen(frame, addScreen(frame));
        });

        // ------------------ Submit functionality ------------------
        submitButton.addActionListener(e -> {
            try {
                // Validate ID
                if (idField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(frame, "Game ID cannot be blank and must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(idField.getText());
                if (id < 0) {
                    JOptionPane.showMessageDialog(frame, "Game ID must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate Title, Genre, Platform
                if (titleField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(frame, "Game Title cannot be blank.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (genreField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(frame, "Genre cannot be blank.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (platformField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(frame, "Platform cannot be blank.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int year = (Integer) yearBox.getSelectedItem();

                // Validate price
                double price = ((Number) priceField.getValue()).doubleValue();
                if (price < 0) {
                    JOptionPane.showMessageDialog(frame, "Price cannot be negative. Enter a valid price.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean multiplayer = multiplayerBox.isSelected();

                VideoGame newGame = new VideoGame(id, titleField.getText(), genreField.getText(), platformField.getText(), year, price, multiplayer);

                if (!collection.addGame(newGame)) {
                    JOptionPane.showMessageDialog(frame, "Game ID already exists or is invalid.", "Add Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                clearButton.doClick();

                // Update view screen
                Container newView = viewScreen(frame);
                swapScreen(frame, newView);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Game ID must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        c = new GridBagConstraints();
        c.gridy = 1;
        c.weighty = 0.6;
        c.anchor = GridBagConstraints.CENTER;
        addPanel.add(middle, c);

        // ===================== BOTTOM =====================
        JPanel bottom = new JPanel();
        JButton returnButton = new JButton("Return");
        returnButton.setFont(new Font("Arial", Font.BOLD, 22));
        returnButton.setPreferredSize(new Dimension(250, 80));

        returnButton.addActionListener(e -> {
            clearButton.doClick();
            swapScreen(frame, homeScreen(frame));
        });

        bottom.add(returnButton);
        c = new GridBagConstraints();
        c.gridy = 2;
        c.weighty = 0.2;
        addPanel.add(bottom, c);

        buttonPanel.setBackground(bgColor);
        addPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return addPanel;
    }

    //method to create and handle update screen gui
    public Container updateScreen(JFrame frame){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel topLabel = new JLabel("Update Games");
        topLabel.setFont(headerFont);
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,0);
        top.add(topLabel, c);
        resetConstraints(c);

        c.gridx = 0;
        c.gridy = 0;
        newPanel.add(top, c);
        resetConstraints(c);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JButton button1 = new JButton("Update Using Title");
        JButton button2 = new JButton("Update Using ID");
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button1.setPreferredSize(buttonDimension);
        button2.setPreferredSize(buttonDimension);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, updateTitle(frame));
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, updateID(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,0,10,0);
        middle.add(button1, c);
        c.gridx = 0;
        c.gridy = 1;
        middle.add(button2, c);


        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.6;
        newPanel.add(middle, c);
        resetConstraints(c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(buttonFont);
        returnButton.setPreferredSize(buttonDimension);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, homeScreen(frame));
            }
        });
        bottom.add(returnButton, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,0,20,0);
        newPanel.add(bottom, c);
        resetConstraints(c);

        newPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return newPanel;

    }

    //method to create and handle remove video game screen
    public Container removeScreen(JFrame frame){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel topLabel = new JLabel("Remove Games");
        topLabel.setFont(headerFont);
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,0);
        top.add(topLabel, c);
        resetConstraints(c);

        c.gridx = 0;
        c.gridy = 0;
        newPanel.add(top, c);
        resetConstraints(c);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JButton button1 = new JButton("Remove Using Title");
        JButton button2 = new JButton("Remove Using ID");
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button1.setPreferredSize(buttonDimension);
        button2.setPreferredSize(buttonDimension);
        c.gridx = 0;
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, removeTitle(frame));
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, removeID(frame));
            }
        });
        c.gridy = 0;
        c.insets = new Insets(10,0,10,0);
        middle.add(button1, c);
        c.gridx = 0;
        c.gridy = 1;
        middle.add(button2, c);


        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.6;
        newPanel.add(middle, c);
        resetConstraints(c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(buttonFont);
        returnButton.setPreferredSize(buttonDimension);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, homeScreen(frame));
            }
        });
        bottom.add(returnButton, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,0,20,0);
        newPanel.add(bottom, c);
        resetConstraints(c);

        newPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return newPanel;
    }

    //method to create and handle inflation calculation screen
    public Container calcScreen(JFrame frame){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel topLabel = new JLabel("Calculate Inflation for Game");
        topLabel.setFont(headerFont);
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,0);
        top.add(topLabel, c);
        resetConstraints(c);

        c.gridx = 0;
        c.gridy = 0;
        newPanel.add(top, c);
        resetConstraints(c);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JButton button1 = new JButton("Select Using Title");
        JButton button2 = new JButton("Select Using ID");
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button1.setPreferredSize(buttonDimension);
        button2.setPreferredSize(buttonDimension);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, calcTitle(frame));
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, calcID(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,0,10,0);
        middle.add(button1, c);
        c.gridx = 0;
        c.gridy = 1;
        middle.add(button2, c);


        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.6;
        newPanel.add(middle, c);
        resetConstraints(c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(buttonFont);
        returnButton.setPreferredSize(buttonDimension);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, homeScreen(frame));
            }
        });
        bottom.add(returnButton, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,0,20,0);
        newPanel.add(bottom, c);
        resetConstraints(c);

        newPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return newPanel;
    }

    //method to create and handle file loading screen
    public Container loadScreen(JFrame frame){
        JPanel loadPanel = new JPanel();
        loadPanel.setLayout(new GridBagLayout());
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel label = new JLabel("File Loader");
        label.setFont(new Font("American Typewriter", Font.BOLD, 40));
        c.gridx = 0;
        c.gridy = 0;
        top.add(label, c);
        c.weighty = 0.2;
        loadPanel.add(top, c);
        resetConstraints(c);
        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JLabel prompt = new JLabel("Choose a .txt file to load.");
        prompt.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridy = 0;
        c.gridx = 0;
        middle.add(prompt, c);
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        fileChooser.addActionListener(e -> {
            if(e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)){
                swapScreen(frame, homeScreen(frame));
            }
            if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    List<String> lines = new ArrayList<>();
                    collection.fileLoader(file.getPath(), lines, collection);
                    Container newView = viewScreen(frame); // rebuild with updated data
                    swapScreen(frame, newView);
                }
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        middle.add(fileChooser, c);
        resetConstraints(c);
        c.gridy = 1;
        c.weighty = 0.7;
        loadPanel.add(middle, c);
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(new Font("Arial", Font.BOLD, 22));
        returnButton.setPreferredSize(new Dimension(250, 80));
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                swapScreen(frame, homeScreen(frame));
            }
        });
        c.insets = new Insets(0,0,20,0);
        bottom.add(returnButton, c);
        resetConstraints(c);
        c.gridy = 2;
        loadPanel.add(bottom, c);
        resetConstraints(c);

        loadPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return loadPanel;
    }

    //method to create and handle about screen gui
    public Container aboutScreen(JFrame frame){
        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new GridBagLayout());
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel label = new JLabel("About This Program");
        label.setFont(new Font("American Typewriter", Font.BOLD, 40));
        top.add(label, c);
        c.weighty = 0.2;
        aboutPanel.add(top, c);
        resetConstraints(c);
        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JTextArea aboutText = new JTextArea(
                "This project is a Video Game Database Management System " +
                "designed to help users organize and manage their personal " +
                "video game collections in an efficient and user-friendly way. " +
                "The application allows users to view, add, update, and remove " +
                "games while keeping track of important details such as title, " +
                "genre, platform, release year, and price. It also includes features " +
                "like file loading to import saved collections and a basic inflation " +
                "calculator to estimate how game prices change over time. Built using " +
                "Java and Swing, this program demonstrates core concepts of " +
                "object-oriented programming, file handling, and graphical user interface " +
                "design, providing both practical functionality and a structured way to manage data."
        );
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setBackground(frame.getBackground());
        aboutText.setPreferredSize(new Dimension(800, 400));
        aboutText.setEditable(false);
        aboutText.setFocusable(false);
        aboutText.setFont(textFont);
        middle.add(aboutText, c);
        resetConstraints(c);
        c.gridy = 1;
        c.weighty = 0.8;
        aboutPanel.add(middle, c);
        resetConstraints(c);
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(new Font("Arial", Font.BOLD, 22));
        returnButton.setPreferredSize(new Dimension(250, 80));
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                swapScreen(frame, homeScreen(frame));
            }
        });
        bottom.add(returnButton, c);
        c.gridy = 2;
        c.weighty = 0.2;
        aboutPanel.add(bottom, c);
        resetConstraints(c);
        aboutPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return aboutPanel;
    }

    //method to create and handle remove games by title screen gui
    public Container removeTitle(JFrame frame){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel topLabel = new JLabel("Remove Game Using Game Title");
        topLabel.setFont(headerFont);
        top.add(topLabel, c);

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,10,20,10);
        newPanel.add(top, c);
        resetConstraints(c);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JLabel fieldLabel = new JLabel("Game Title:");
        fieldLabel.setHorizontalAlignment(JLabel.RIGHT);
        fieldLabel.setFont(textFont);
        c.gridx = 0;
        c.gridy = 0;
        middle.add(fieldLabel,c);
        JTextField textField = new JTextField(10);
        textField.setFont(textFont);
        c.gridx = 1;
        c.gridy = 0;
        middle.add(textField, c);
        JButton submitButton = new JButton("Remove");
        JButton resetButton = new JButton("Clear");
        submitButton.setPreferredSize(buttonDimension);
        submitButton.setFont(buttonFont);
        resetButton.setPreferredSize(buttonDimension);
        resetButton.setFont(buttonFont);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, removeTitle(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,5,10,5);
        middle.add(resetButton, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(20,10,20,10);
        middle.add(submitButton, c);
        JLabel oldPrice = new JLabel("   ");
        JLabel newPrice = new JLabel("   ");
        oldPrice.setFont(textFont);
        newPrice.setFont(textFont);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(10,5,10,5);
        middle.add(oldPrice, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(10,5,10,5);
        middle.add(newPrice, c);
        resetConstraints(c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.6;
        middle.setBorder(new LineBorder(Color.DARK_GRAY));
        newPanel.add(middle, c);
        resetConstraints(c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(buttonFont);
        returnButton.setPreferredSize(buttonDimension);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, removeScreen(frame));
            }
        });
        c.insets = new Insets(10,10,20,10);
        bottom.add(returnButton, c);

        c.gridx = 0;
        c.gridy = 2;
        newPanel.add(bottom, c);
        resetConstraints(c);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VideoGame game = collection.searchByGameTitle(textField.getText());
                if(game == null){
                    oldPrice.setText("Game Title not Found.");
                } else {
                    oldPrice.setText(game.getGameTitle() +
                     collection.deleteGameByTitle(game.getGameTitle()));
                    swapScreen(frame, viewScreen(frame));
                }

            }
        });

        newPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return newPanel;
    }

    //method to create and handle remove games by id gui
    public Container removeID(JFrame frame){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel topLabel = new JLabel("Remove Game Using Game ID");
        topLabel.setFont(headerFont);
        top.add(topLabel, c);

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,10,20,10);
        newPanel.add(top, c);
        resetConstraints(c);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JLabel fieldLabel = new JLabel("Game ID:");
        fieldLabel.setHorizontalAlignment(JLabel.RIGHT);
        fieldLabel.setFont(textFont);
        c.gridx = 0;
        c.gridy = 0;
        middle.add(fieldLabel,c);
        JTextField textField = new JTextField(10);
        textField.setFont(textFont);
        c.gridx = 1;
        c.gridy = 0;
        middle.add(textField, c);
        JButton submitButton = new JButton("Remove");
        JButton resetButton = new JButton("Clear");
        submitButton.setPreferredSize(buttonDimension);
        submitButton.setFont(buttonFont);
        resetButton.setPreferredSize(buttonDimension);
        resetButton.setFont(buttonFont);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, removeID(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,5,10,5);
        middle.add(resetButton, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(20,10,20,10);
        middle.add(submitButton, c);
        JLabel oldPrice = new JLabel("   ");
        JLabel newPrice = new JLabel("   ");
        oldPrice.setFont(textFont);
        newPrice.setFont(textFont);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(10,5,10,5);
        middle.add(oldPrice, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(10,5,10,5);
        middle.add(newPrice, c);
        resetConstraints(c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.6;
        middle.setBorder(new LineBorder(Color.DARK_GRAY));
        newPanel.add(middle, c);
        resetConstraints(c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(buttonFont);
        returnButton.setPreferredSize(buttonDimension);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, removeScreen(frame));
            }
        });
        c.insets = new Insets(10,10,20,10);
        bottom.add(returnButton, c);

        c.gridx = 0;
        c.gridy = 2;
        newPanel.add(bottom, c);
        resetConstraints(c);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VideoGame game = collection.searchByGameID(Integer.parseInt(textField.getText()));
                if(game == null){
                    oldPrice.setText("Game ID not Found.");
                } else {
                    oldPrice.setText(game.getGameTitle() +
                    collection.deleteGameByID(game.getGameID()));
                    swapScreen(frame, viewScreen(frame));
                }

            }
        });

        newPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return newPanel;
    }

    //method to create and handle update games by id gui
    public Container updateID(JFrame frame){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel topLabel = new JLabel("Update Game Using Game ID");
        topLabel.setFont(headerFont);
        top.add(topLabel, c);

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,10,20,10);
        newPanel.add(top, c);
        resetConstraints(c);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JLabel fieldLabel = new JLabel("Game ID:");
        fieldLabel.setHorizontalAlignment(JLabel.RIGHT);
        fieldLabel.setFont(textFont);
        c.gridx = 0;
        c.gridy = 0;
        middle.add(fieldLabel,c);
        JTextField textField = new JTextField(10);
        textField.setFont(textFont);
        c.gridx = 1;
        c.gridy = 0;
        middle.add(textField, c);
        JButton submitButton = new JButton("Update");
        JButton resetButton = new JButton("Clear");
        submitButton.setPreferredSize(buttonDimension);
        submitButton.setFont(buttonFont);
        resetButton.setPreferredSize(buttonDimension);
        resetButton.setFont(buttonFont);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, updateID(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,5,10,5);
        middle.add(resetButton, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(20,10,20,10);
        middle.add(submitButton, c);
        JLabel oldPrice = new JLabel("   ");
        JLabel newPrice = new JLabel("   ");
        oldPrice.setFont(textFont);
        newPrice.setFont(textFont);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(10,5,10,5);
        middle.add(oldPrice, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(10,5,10,5);
        middle.add(newPrice, c);
        resetConstraints(c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.6;
        middle.setBorder(new LineBorder(Color.DARK_GRAY));
        newPanel.add(middle, c);
        resetConstraints(c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(buttonFont);
        returnButton.setPreferredSize(buttonDimension);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, updateScreen(frame));
            }
        });
        c.insets = new Insets(10,10,20,10);
        bottom.add(returnButton, c);

        c.gridx = 0;
        c.gridy = 2;
        newPanel.add(bottom, c);
        resetConstraints(c);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VideoGame game = collection.searchByGameID(Integer.parseInt(textField.getText()));
                if(game == null){
                    oldPrice.setText("Game ID not Found.");
                } else {
                    swapScreen(frame, updateGame(frame, game));
                }

            }
        });

        newPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return newPanel;
    }

    //method to create and handle update games by title gui
    public Container updateTitle(JFrame frame){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel topLabel = new JLabel("Update Game Using Game Title");
        topLabel.setFont(headerFont);
        top.add(topLabel, c);

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,10,20,10);
        newPanel.add(top, c);
        resetConstraints(c);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JLabel fieldLabel = new JLabel("Game Title:");
        fieldLabel.setHorizontalAlignment(JLabel.RIGHT);
        fieldLabel.setFont(textFont);
        c.gridx = 0;
        c.gridy = 0;
        middle.add(fieldLabel,c);
        JTextField textField = new JTextField(10);
        textField.setFont(textFont);
        c.gridx = 1;
        c.gridy = 0;
        middle.add(textField, c);
        JButton submitButton = new JButton("Update");
        JButton resetButton = new JButton("Clear");
        submitButton.setPreferredSize(buttonDimension);
        submitButton.setFont(buttonFont);
        resetButton.setPreferredSize(buttonDimension);
        resetButton.setFont(buttonFont);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, updateTitle(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,5,10,5);
        middle.add(resetButton, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(20,10,20,10);
        middle.add(submitButton, c);
        JLabel oldPrice = new JLabel("   ");
        JLabel newPrice = new JLabel("   ");
        oldPrice.setFont(textFont);
        newPrice.setFont(textFont);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(10,5,10,5);
        middle.add(oldPrice, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(10,5,10,5);
        middle.add(newPrice, c);
        resetConstraints(c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.6;
        middle.setBorder(new LineBorder(Color.DARK_GRAY));
        newPanel.add(middle, c);
        resetConstraints(c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(buttonFont);
        returnButton.setPreferredSize(buttonDimension);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, updateScreen(frame));
            }
        });
        c.insets = new Insets(10,10,20,10);
        bottom.add(returnButton, c);

        c.gridx = 0;
        c.gridy = 2;
        newPanel.add(bottom, c);
        resetConstraints(c);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VideoGame game = collection.searchByGameTitle(textField.getText());
                if(game == null){
                    oldPrice.setText("Game ID not Found.");
                } else {
                    swapScreen(frame, updateGame(frame, game));
                }

            }
        });

        newPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return newPanel;
    }

    //method to create and handle calculate inflation by title gui
    public Container calcTitle(JFrame frame){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel topLabel = new JLabel("Calculate Interest For Game Title");
        topLabel.setFont(headerFont);
        top.add(topLabel, c);

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,10,20,10);
        newPanel.add(top, c);
        resetConstraints(c);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JLabel fieldLabel = new JLabel("Game Title:");
        fieldLabel.setHorizontalAlignment(JLabel.RIGHT);
        fieldLabel.setFont(textFont);
        c.gridx = 0;
        c.gridy = 0;
        middle.add(fieldLabel,c);
        JTextField textField = new JTextField(10);
        textField.setFont(textFont);
        c.gridx = 1;
        c.gridy = 0;
        middle.add(textField, c);
        JButton submitButton = new JButton("Calculate");
        JButton resetButton = new JButton("Clear");
        submitButton.setPreferredSize(buttonDimension);
        submitButton.setFont(buttonFont);
        resetButton.setPreferredSize(buttonDimension);
        resetButton.setFont(buttonFont);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, calcTitle(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,5,10,5);
        middle.add(resetButton, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(20,10,20,10);
        middle.add(submitButton, c);
        JLabel oldPrice = new JLabel("   ");
        JLabel newPrice = new JLabel("   ");
        oldPrice.setFont(textFont);
        newPrice.setFont(textFont);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(10,5,10,5);
        middle.add(oldPrice, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(10,5,10,5);
        middle.add(newPrice, c);
        resetConstraints(c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.6;
        middle.setBorder(new LineBorder(Color.DARK_GRAY));
        newPanel.add(middle, c);
        resetConstraints(c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(buttonFont);
        returnButton.setPreferredSize(buttonDimension);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, calcScreen(frame));
            }
        });
        c.insets = new Insets(10,10,20,10);
        bottom.add(returnButton, c);

        c.gridx = 0;
        c.gridy = 2;
        newPanel.add(bottom, c);
        resetConstraints(c);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oldPrice.setText(" ");
                newPrice.setText(" ");
                VideoGame game = collection.searchByGameTitle(textField.getText());
                if(game == null){
                    oldPrice.setText("Game Title not Found.");
                } else {
                    oldPrice.setText(game.getGameTitle() +
                            "'s Release Price: " + game.getGameReleasePrice());
                    newPrice.setText(game.getGameTitle() +
                            "'s Inflated Price: " + game.calculateInflationPrice());
                }

            }
        });

        newPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return newPanel;
    }

    //method to create and handle calculate inflation by id gui
    public Container calcID(JFrame frame){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        JLabel topLabel = new JLabel("Calculate Interest For Game ID");
        topLabel.setFont(headerFont);
        top.add(topLabel, c);

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,10,20,10);
        newPanel.add(top, c);
        resetConstraints(c);

        JPanel middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        JLabel fieldLabel = new JLabel("Game ID:");
        fieldLabel.setHorizontalAlignment(JLabel.RIGHT);
        fieldLabel.setFont(textFont);
        c.gridx = 0;
        c.gridy = 0;
        middle.add(fieldLabel,c);
        JTextField textField = new JTextField(10);
        textField.setFont(textFont);
        c.gridx = 1;
        c.gridy = 0;
        middle.add(textField, c);
        JButton submitButton = new JButton("Calculate");
        JButton resetButton = new JButton("Clear");
        submitButton.setPreferredSize(buttonDimension);
        submitButton.setFont(buttonFont);
        resetButton.setPreferredSize(buttonDimension);
        resetButton.setFont(buttonFont);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, calcID(frame));
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,5,10,5);
       middle.add(resetButton, c);
       c.gridx = 1;
       c.gridy = 1;
       c.insets = new Insets(20,10,20,10);
       middle.add(submitButton, c);
       JLabel oldPrice = new JLabel("   ");
       JLabel newPrice = new JLabel("   ");
       oldPrice.setFont(textFont);
       newPrice.setFont(textFont);
       c.gridx = 0;
       c.gridy = 2;
       c.gridwidth = 2;
       c.insets = new Insets(10,5,10,5);
       middle.add(oldPrice, c);
       c.gridx = 0;
       c.gridy = 3;
       c.gridwidth = 2;
       c.insets = new Insets(10,5,10,5);
       middle.add(newPrice, c);
       resetConstraints(c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.6;
        middle.setBorder(new LineBorder(Color.DARK_GRAY));
        newPanel.add(middle, c);
        resetConstraints(c);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setFont(buttonFont);
        returnButton.setPreferredSize(buttonDimension);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapScreen(frame, calcScreen(frame));
            }
        });
        c.insets = new Insets(10,10,20,10);
        bottom.add(returnButton, c);

        c.gridx = 0;
        c.gridy = 2;
        newPanel.add(bottom, c);
        resetConstraints(c);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oldPrice.setText(" ");
                newPrice.setText(" ");
                if(textField.getText().isBlank()){
                    oldPrice.setText("Game ID not Found.");
                }
                else {
                    VideoGame game = collection.searchByGameID(Integer.parseInt(textField.getText()));
                    if (game == null) {
                        oldPrice.setText("Game ID not Found.");
                    } else {
                        oldPrice.setText(game.getGameTitle() +
                                "'s Release Price: " + game.getGameReleasePrice());
                        newPrice.setText(game.getGameTitle() +
                                "'s Inflated Price: " + game.calculateInflationPrice());
                    }
                }
            }
        });

        newPanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return newPanel;
    }

    //method to create and handle update game in form screen gui
    public Container updateGame(JFrame frame, VideoGame game){
        JPanel updatePanel = new JPanel(new GridBagLayout());

        // ===================== TOP =====================
        JPanel top = new JPanel(new GridBagLayout());
        JLabel title = new JLabel("Update Game");
        title.setFont(new Font("American Typewriter", Font.BOLD, 40));
        top.add(title);

        c.gridy = 0;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.PAGE_END;
        updatePanel.add(top, c);

        // ===================== MIDDLE FORM =====================
        JPanel middle = new JPanel(new GridBagLayout());
        middle.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints form = new GridBagConstraints();
        form.insets = new Insets(10, 10, 10, 10);

        String[] labels = {
                "Game ID:", "Game Title:", "Genre:",
                "Platform:", "Release Year:", "Price:", "Multiplayer:"
        };

        JTextField idField = new JTextField(20);
        JTextField titleField = new JTextField(20);
        JTextField genreField = new JTextField(20);
        JTextField platformField = new JTextField(20);

        // ID digits only
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d+")) super.insertString(fb, offset, string, attr);
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d+")) super.replace(fb, offset, length, text, attrs);
            }
        });
        idField.setDocument(doc);

        // Year combo box
        int currentYear = java.time.Year.now().getValue();
        Integer[] years = new Integer[currentYear - 1958 + 1];
        for (int i = 0; i < years.length; i++) years[i] = 1958 + i;
        JComboBox<Integer> yearBox = new JComboBox<>(years);
        yearBox.setSelectedItem(currentYear);

        // Price formatted
        NumberFormat priceFormat = NumberFormat.getNumberInstance();
        priceFormat.setMinimumFractionDigits(2);
        priceFormat.setMaximumFractionDigits(2);
        priceFormat.setGroupingUsed(false);
        JFormattedTextField priceField = new JFormattedTextField(priceFormat);
        priceField.setColumns(20);
        priceField.setValue(0.0);

        // Multiplayer checkbox
        JCheckBox multiplayerBox = new JCheckBox("Multiplayer");

        JComponent[] inputs = {idField, titleField, genreField, platformField, yearBox, priceField, multiplayerBox};

        for (int i = 0; i < labels.length; i++) {
            form.gridx = 0;
            form.gridy = i;
            form.anchor = GridBagConstraints.EAST;
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(new Font("Arial", Font.BOLD, 24));
            middle.add(lbl, form);

            form.gridx = 1;
            form.anchor = GridBagConstraints.WEST;
            inputs[i].setPreferredSize(new Dimension(250, 30));
            middle.add(inputs[i], form);
        }
        // ===================== BUTTONS =====================
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton clearButton = new JButton("Clear");
        JButton submitButton = new JButton("Update Game");

        clearButton.setFont(buttonFont);
        clearButton.setPreferredSize(buttonDimension);
        submitButton.setFont(buttonFont);
        submitButton.setPreferredSize(buttonDimension);

        buttonPanel.add(clearButton);
        buttonPanel.add(submitButton);

        form.gridx = 0;
        form.gridy = labels.length;
        form.gridwidth = 2;
        form.anchor = GridBagConstraints.CENTER;
        middle.add(buttonPanel, form);

        // ------------------ Clear functionality ------------------
        clearButton.addActionListener(e -> {
            idField.setText(String.valueOf(game.getGameID()));
            titleField.setText(game.getGameTitle());
            genreField.setText(game.getGameGenre());
            platformField.setText(game.getGamePlatform());
            priceField.setText(String.valueOf(game.getGameReleasePrice()));
            yearBox.setSelectedItem(game.getGameReleaseYear());
            multiplayerBox.setSelected(game.isMultiplayer());
        });

        // ------------------ Submit functionality ------------------
        submitButton.addActionListener(e -> {
            try {
                VideoGame temp = game;
                collection.deleteGameByID(game.getGameID());

                // Validate ID
                if (idField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(frame, "Game ID cannot be blank and must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    collection.addGame(temp);
                    return;
                }
                int id = Integer.parseInt(idField.getText());
                if (id < 0) {
                    JOptionPane.showMessageDialog(frame, "Game ID must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    collection.addGame(temp);
                    return;
                }

                // Validate Title, Genre, Platform
                if (titleField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(frame, "Game Title cannot be blank.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    collection.addGame(temp);
                    return;
                }
                if (genreField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(frame, "Genre cannot be blank.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    collection.addGame(temp);
                    return;
                }
                if (platformField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(frame, "Platform cannot be blank.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    collection.addGame(temp);
                    return;
                }

                 int year = (Integer) yearBox.getSelectedItem();

                // Validate price
                double price = ((Number) priceField.getValue()).doubleValue();
                if (price < 0) {
                    JOptionPane.showMessageDialog(frame, "Price cannot be negative. Enter a valid price.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    collection.addGame(temp);
                    return;
                }

                boolean multiplayer = multiplayerBox.isSelected();

                VideoGame newGame = new VideoGame(id, titleField.getText(), genreField.getText(), platformField.getText(), year, price, multiplayer);

                if (!collection.addGame(newGame)) {
                    JOptionPane.showMessageDialog(frame, "Game ID already exists or is invalid.", "Add Error", JOptionPane.ERROR_MESSAGE);
                    collection.addGame(temp);
                    return;
                }

                clearButton.doClick();

                // Update view screen
                Container newView = viewScreen(frame);
                swapScreen(frame, newView);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Game ID must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        c = new GridBagConstraints();
        c.gridy = 1;
        c.weighty = 0.6;
        c.anchor = GridBagConstraints.CENTER;
        updatePanel.add(middle, c);

        // ===================== BOTTOM =====================
        JPanel bottom = new JPanel();
        JButton returnButton = new JButton("Return");
        returnButton.setFont(new Font("Arial", Font.BOLD, 22));
        returnButton.setPreferredSize(new Dimension(250, 80));

        returnButton.addActionListener(e -> {
            clearButton.doClick();
            swapScreen(frame, updateScreen(frame));
        });

        bottom.add(returnButton);
        c = new GridBagConstraints();
        c.gridy = 2;
        c.weighty = 0.2;
        updatePanel.add(bottom, c);

        idField.setText(String.valueOf(game.getGameID()));
        titleField.setText(game.getGameTitle());
        genreField.setText(game.getGameGenre());
        platformField.setText(game.getGamePlatform());
        priceField.setText(String.valueOf(game.getGameReleasePrice()));
        yearBox.setSelectedItem(game.getGameReleaseYear());
        multiplayerBox.setSelected(game.isMultiplayer());

        buttonPanel.setBackground(bgColor);
        updatePanel.setBackground(bgColor);
        top.setBackground(bgColor);
        middle.setBackground(bgColor);
        bottom.setBackground(bgColor);
        return updatePanel;
    }
}