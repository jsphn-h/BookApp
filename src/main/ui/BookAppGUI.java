package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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
    private JButton displayWishlist = new JButton();

    // EFFECTS: creates bookApp so we can use its functions
    public BookAppGUI() throws FileNotFoundException {
        bookApp = new BookApp();
    }

    // EFFECTS: adding menu buttons to startup window
    public void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addButton(displayListsButton(), pane);
        addButton(displayAllBooksButton(), pane);
        addButton(booksInListButton(), pane);
        addButton(addBook(), pane);
        addButton(removeBook(), pane);
        addButton(saveLibrary(), pane);
        addButton(loadLibrary(), pane);
    }

    // EFFECTS: creates a button and adds it to the container
    private void addButton(JButton button, Container container) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }

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

    // EFFECTS: creates a window to display all books in the library
    private void allBooksWindow() {
        JFrame allBooks = createWindow("All Books");
        //Scroll pane is in JFrame
        //Scroll pane contains JPanel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

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
//        scrollPane.add(panel);
        allBooks.add(panel);
//        allBooks.add(scrollPane);
    }

    // EFFECTS: creates a button to display books in a user selected list
    private JButton booksInListButton() {
        displayBooksButton.setText("Display books in list");
        displayBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectList();
            }
        });
        return displayBooksButton;
    }

    // EFFECTS: creates a popup window for the user to select a list
    private void selectList() {
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());

        panel.add(new JLabel("Please select a list:  "));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Wishlist");
        model.addElement("Read");
        model.addElement("Reading");
        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "List",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String choice = "";
        switch (result) {
            case JOptionPane.OK_OPTION:
                choice += comboBox.getSelectedItem();
                break;
        }
        displayBookLists(choice);
    }

    // EFFECTS: processes the user selection and displays the corresponding list
    private void displayBookLists(String choice) {
        switch (choice) {
            case "Wishlist":
                displayWishlist();
                break;
            case "Read":
                displayRead();
                break;
            case "Reading":
                displayReading();
                break;
        }
    }

    // EFFECTS: displays the books in "Wishlist"
    private void displayWishlist() {
        JFrame frame = createWindow("Wishlist");

        ArrayList books = bookApp.displayBooksInList("Wishlist");
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

    // EFFECTS: displays the books in "Read"
    private void displayRead() {
        JFrame frame = createWindow("Read");
        ArrayList books = bookApp.displayBooksInList("Read");
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

    // EFFECTS: displays the books in "Reading"
    private void displayReading() {
        JFrame frame = createWindow("Reading");
        ArrayList books = bookApp.displayBooksInList("Reading");
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

    // EFFECTS: creates a button to add a book to a list
    private JButton addBook() {
        addBookButton.setText("Add book to list");
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(addBookButton, addBookToList());
            }
        });
        return addBookButton;
    }

    // EFFECTS: creates a window for the user to enter the book information and select a list to add it to
    private String addBookToList() {
        String message = "";
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        createLabels(panel, c);
        JTextField titleField = createTextField(20, 1, 0, c);
        panel.add(titleField, c);
        JTextField authorField = createTextField(20, 1, 1, c);
        panel.add(authorField, c);
        JTextField seriesNumField = createTextField(20, 1, 2, c);
        panel.add(seriesNumField, c);

        String genre = selectGenre(panel, c);
        JComboBox comboBox = createComboBox(panel, c);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Book to List",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (result) {
            case JOptionPane.OK_OPTION:
                String[] userAnswers = getUserAnswer(titleField.getText(), authorField.getText(), seriesNumField.getText()); //HOW TO GET USER INPUT FROM FIELDS?
                message = processInputs(userAnswers, genre, comboBox.getSelectedItem().toString());
                break;
        }
        return message;
    }

    // EFFECTS: creates labels for entering the book information page
    private void createLabels(JPanel panel, GridBagConstraints c) {
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

    // EFFECTS: puts the user answers into a String array and returns it
    private String[] getUserAnswer(String title, String author) {
        String[] userAnswer = new String[2];

        userAnswer[0] = title;
        userAnswer[1] = author;

        return userAnswer;
    }

    // EFFECTS: puts the user answers into a String array and returns the array
    private String[] getUserAnswer(String title, String author, String seriesNum) {
        String[] userAnswer = new String[3];

        userAnswer[0] = title;
        userAnswer[1] = author;
        userAnswer[2] = seriesNum;

        return userAnswer;
    }

    // EFFECTS: creates a ComboBox on the page so the user can select a list
    private JComboBox createComboBox(JPanel panel, GridBagConstraints c) {
        JLabel label = createLabel("Please select a list:  ", 0, 4, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Wishlist");
        model.addElement("Read");
        model.addElement("Reading");
        JComboBox comboBox = new JComboBox(model);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        panel.add(comboBox, c);

        return comboBox;
    }

    // EFFECTS: processes the user input and removes the book from the library
    private String processInputs(String[] bookInfo) {
        String title = bookInfo[0];
        String author = bookInfo[1];
        String message = bookApp.removeBook(title, author);
        return message;
    }

    // EFFECTS: processes the user input and adds book to library
    private String processInputs(String[] bookInfo, String genre, String list) {
        String title = bookInfo[0];
        String author = bookInfo[1];
        int seriesNum = Integer.parseInt(bookInfo[2]);
        String message = bookApp.addToList(title, author, seriesNum, genre, list);
        return message;
    }

    // EFFECTS: creates a button to remove a book from the library
    private JButton removeBook() {
        removeBookButton.setText("Remove book from list");
        removeBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(removeBookButton, removeBookFromList());
            }
        });
        return removeBookButton;
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
                String[] userAnswers = getUserAnswer(titleField.getText(), authorField.getText());
                message = processInputs(userAnswers);
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

    // EFFECTS: creates a button to save the library
    private JButton saveLibrary() {
        saveLibButton.setText("Save library");
        saveLibButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(saveLibButton, bookApp.saveLibrary());
            }
        });
        return saveLibButton;
    }

    // EFFECTS: creates a button to load the library
    private JButton loadLibrary() {
        loadLibButton.setText("Load library");
        loadLibButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(loadLibButton, bookApp.loadLibrary());
            }
        });
        return loadLibButton;
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

    // EFFECTS: creates a window
    private JFrame createWindow(String title) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    // EFFECTS: sets up and creates GUI
    public void createAndShowGUI() {
        // Create window
        JFrame frame = new JFrame("BookApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up content pane
        frame.setPreferredSize(new Dimension(400, 400));
        addComponentsToPane(frame.getContentPane());

        // Display window
        frame.pack();
        frame.setVisible(true);
    }
}
