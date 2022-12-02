package ui;

import exception.LogException;
import model.Event;
import model.EventLog;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// creates a GUI for BookApp so users only have to interact with it (and not the console)
public class BookAppGUI extends JFrame {
    private BookApp bookApp;

    private JButton displayListsButton = new JButton();
    private JButton displayAllButton = new JButton();
    private JButton displayBooksButton = new JButton();
    private JButton addBookButton = new JButton();
    private JButton removeBookButton = new JButton();
    private JButton saveLibButton = new JButton();
    private JButton loadLibButton = new JButton();
    private JLabel bookImage = new JLabel();

    // EFFECTS: creates bookApp so we can use its functions
    public BookAppGUI() throws FileNotFoundException {
        bookApp = new BookApp();
    }

    // EFFECTS: adding image and menu buttons to startup window
    public void menuScreen(Container pane) throws IOException {
        pane.setLayout(new FlowLayout());
        imagePanel(pane);
        menuPanel(pane);
    }

    // EFFECTS: creates a button and adds it to the container
    private void addButton(JButton button, Container pane, GridBagConstraints c, int width, int height, int x, int y) {
        button.setSize(width, height);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = x;
        c.gridy = y;
        c.ipady = 20;
        c.gridheight = 1;
        pane.add(button, c);
    }

    // EFFECTS: adding book image to the container
    private void imagePanel(Container container) throws IOException {
        JPanel imagePanel = new JPanel();

        // Getting and formatting image
        BufferedImage image = ImageIO.read(getClass().getResource("/images/books.jpeg"));
        Image resultingImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(resultingImage));

        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(picLabel);
    }

    // Menu panel with buttons so user can interact with app
    private void menuPanel(Container container) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        addButton(displayListsButton(), menuPanel, c, 200, 10, 0, 2);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        addButton(displayAllBooksButton(), menuPanel, c, 200, 20, 0, 3);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        addButton(booksInListButton(), menuPanel, c, 200, 20, 0, 4);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        addButton(addBookButton(), menuPanel, c, 200, 20, 0, 5);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        addButton(removeBookButton(), menuPanel, c, 200, 20, 0, 6);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        addButton(saveLibraryButton(), menuPanel, c, 200, 20, 0, 7);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        addButton(loadLibraryButton(), menuPanel, c, 200, 20, 0, 8);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        container.add(menuPanel);
    }

    // ***** MENU BUTTONS ***** //

    // EFFECTS: creates a button to display the lists in the library
    private JButton displayListsButton() {
        displayListsButton.setText("Display lists");
        displayListsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(displayAllButton, bookApp.getLists());
            }
        });
        return displayListsButton;
    }

    // EFFECTS: creates a button to display all the books in the library
    private JButton displayAllBooksButton() {
        displayAllButton.setText("Display all books");
        displayAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                allBooksWindow();
            }
        });
        return displayAllButton;
    }

    // EFFECTS: creates a button to display books in a user selected list
    private JButton booksInListButton() {
        displayBooksButton.setText("Display books in list");
        displayBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectListPopup();
            }
        });
        return displayBooksButton;
    }
    
    // EFFECTS: creates a button to add a book to a list
    private JButton addBookButton() {
        addBookButton.setText("Add book to list");
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(addBookButton, addBookWindow());
            }
        });
        return addBookButton;
    }
    
    // EFFECTS: creates a button to remove a book from the library
    private JButton removeBookButton() {
        removeBookButton.setText("Remove book from library");
        removeBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(removeBookButton, removeBookFromList());
            }
        });
        return removeBookButton;
    }

    // EFFECTS: creates a button to save the library
    private JButton saveLibraryButton() {
        saveLibButton.setText("Save library");
        saveLibButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(saveLibButton, bookApp.saveLibrary());
            }
        });
        return saveLibButton;
    }

    // EFFECTS: creates a button to load the library
    private JButton loadLibraryButton() {
        loadLibButton.setText("Load library");
        loadLibButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(loadLibButton, bookApp.loadLibrary());
            }
        });
        return loadLibButton;
    }


    // *** SPECIFIC FUNCTIONS*** //

    // EFFECTS: creates a window to display all books in the library
    private void allBooksWindow() {
        JFrame allBooks = createWindow("All Books");
        ArrayList books = bookApp.displayAllBooks();
        int rows = books.size() / 2;
        int cols = 2;

        String bookInfo;
        JPanel panel = new JPanel(new GridLayout(rows, cols, 5, 5));
        for (int i = 0; i < books.size(); i++) {
            bookInfo = books.get(i).toString();
            JTextArea textBox = new JTextArea(bookInfo);
            textBox.setLineWrap(true);
            panel.add(textBox);
            panel.setVisible(true);
        }
        allBooks.add(panel);
    }

    // EFFECTS: creates a popup window for the user to select a list
    private void selectListPopup() {
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());

        panel.add(new JLabel("Please select a list: "));
        JComboBox comboBox = selectList();
        panel.add(comboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "List",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String choice = "";
        switch (result) {
            case JOptionPane.OK_OPTION:
                choice += comboBox.getSelectedItem();
                break;
        }
        processListSelection(choice);
    }

    // EFFECTS: processes the user selection and displays the corresponding list
    private void processListSelection(String choice) {
        switch (choice) {
            case "Wishlist":
                displayBooksInList("Wishlist");
                break;
            case "Read":
                displayBooksInList("Read");
                break;
            case "Reading":
                displayBooksInList("Reading");
                break;
        }
    }

    // EFFECTS: displays the books in the list
    private void displayBooksInList(String text) {
        JFrame frame = createWindow(text);

        ArrayList books = bookApp.displayBooksInList(text);
        int rows = books.size() / 2;
        int cols = 2;

        String bookInfo;
        JPanel panel = new JPanel(new GridLayout(rows, cols, 5, 5));
        for (int i = 0; i < books.size(); i++) {
            bookInfo = books.get(i).toString();
            JTextArea textBox = new JTextArea(bookInfo);
            textBox.setLineWrap(true);
            panel.add(textBox);
            panel.setVisible(true);
        }
        frame.add(panel);
    }

    // EFFECTS: creates a popup window for the user to enter the book information and select a list to add it to
    private String addBookWindow() {
        String message = "";
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        createBookInfoLabels(panel, c);
        JTextField titleField = createTextField(20, 1, 0, c);
        panel.add(titleField, c);
        JTextField authorField = createTextField(20, 1, 1, c);
        panel.add(authorField, c);
        JTextField seriesNumField = createTextField(20, 1, 2, c);
        panel.add(seriesNumField, c);

        String genre = selectGenre(panel, c);
        JComboBox comboBox = createSelectBox(panel, c, 0, 4, 1, 4);
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Book to List",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (result) {
            case JOptionPane.OK_OPTION:
                message = addBook(titleField.getText(), authorField.getText(), seriesNumField.getText(), genre,
                        comboBox.getSelectedItem().toString());
                break;
            default:
                message = "No changes made.";
        }
        return message;
    }

    // EFFECTS: creates labels for entering the book information page
    private void createBookInfoLabels(JPanel panel, GridBagConstraints c) {
        JLabel label;

        label = createLabel("Title: ", 0, 0, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);

        label = createLabel("Author: ", 0, 1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);

        label = createLabel("Book x in series: ", 0, 2, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);
    }

    // EFFECTS: creates a ComboBox on the page so the user can select a list
    private JComboBox createSelectBox(JPanel panel, GridBagConstraints c,  int x, int y, int x2, int y2) {
        JLabel label = createLabel("Please select a list: ", x, y, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);

        JComboBox comboBox = selectList();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = x2;
        c.gridy = y2;
        panel.add(comboBox, c);

        return comboBox;
    }

    // EFFECTS: processes the user input and adds book to library
    private String addBook(String title, String author, String num, String genre, String list) {
        int seriesNum = Integer.parseInt(num);
        String message = bookApp.addToList(title, author, seriesNum, genre, list);
        return message;
    }

    // EFFECTS: processes the user input and removes the book from the library
    private String removeBook(String title, String author) {
        String message = bookApp.removeBook(title, author);
        return message;
    }

    // EFFECTS: creates popup window for user to enter book information
    private String removeBookFromList() {
        String message = "";
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        createRemoveLabels(panel, c);
        JTextField titleField = createTextField(20, 1, 0, c);
        panel.add(titleField, c);
        JTextField authorField = createTextField(20, 1, 1, c);
        panel.add(authorField, c);

        int result = JOptionPane.showConfirmDialog(null, panel, "Remove Book from Library",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (result) {
            case JOptionPane.OK_OPTION:
                message = removeBook(titleField.getText(), authorField.getText());
                break;
            case JOptionPane.CANCEL_OPTION:
                message = "No changes made.";
                break;
        }
        return message;
    }

    // EFFECTS: creates the labels on the remove page
    private void createRemoveLabels(JPanel panel, GridBagConstraints c) {
        JLabel label;

        label = createLabel("Title: ", 0, 0, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);

        label = createLabel("Author: ", 0, 1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);
    }

    // ***** MULTI-USE FUNCTIONS ******///
    // EFFECTS: creates a label with the specified text, gridx and gridy
    private JLabel createLabel(String text, int x, int y, GridBagConstraints c) {
        JLabel label = new JLabel(text);
        c.gridx = x;
        c.gridy = y;
        return label;
    }

    // EFFECTS: creates a label with the specified width (col), text, gridx and gridy
    private JTextField createTextField(int col, int x, int y, GridBagConstraints c) {
        JTextField textField = new JTextField(20);
        c.gridx = x;
        c.gridy = y;
        return textField;
    }

    // EFFECTS: creates a selection box for the user to select a list
    private JComboBox selectList() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Wishlist");
        model.addElement("Read");
        model.addElement("Reading");
        JComboBox comboBox = new JComboBox(model);
        return  comboBox;
    }

    // EFFECTS: creates a selection box for the user to select a genre
    private String selectGenre(JPanel panel, GridBagConstraints c) {
        JLabel label = new JLabel("Please select a genre:  ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(label, c);

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Fiction");
        model.addElement("Romance");
        model.addElement("Fantasy");
        model.addElement("Mystery");
        model.addElement("Thriller");
        model.addElement("Action");
        model.addElement("Horror");
        model.addElement("Sci-Fi");
        model.addElement("Nonfiction");
        JComboBox comboBox = new JComboBox(model);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        panel.add(comboBox, c);

        String choice = "" + comboBox.getSelectedItem();

        return choice;
    }


    // EFFECTS: puts the user answers into a String array and returns it
    private String[] getUserAnswer(String title, String author) {
        String[] userAnswer = new String[2];

        userAnswer[0] = title;
        userAnswer[1] = author;

        return userAnswer;
    }

    // EFFECTS: creates a window
    private JFrame createWindow(String title) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    // EFFECTS: sets up and creates GUI
    public void createAndShowGUI() throws IOException {
        // Create window
        JFrame frame = new JFrame("BookApp");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    printLog(EventLog.getInstance());
                } catch (LogException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            }
        });


        // Set up content pane
        frame.setPreferredSize(new Dimension(400, 400));
        menuScreen(frame.getContentPane());

        // Display window
        frame.pack();
        frame.setVisible(true);
    }

    public void printLog(EventLog el) throws LogException {
        for (Event e: el) {
            System.out.println(e.toString());
        }
    }
}
