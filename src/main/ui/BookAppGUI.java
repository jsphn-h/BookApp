package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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

    public BookAppGUI() throws FileNotFoundException {
        bookApp = new BookApp();
    }

    public void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addButton(displayLists(), pane);
        addButton(displayAllBooks(), pane);
        addButton(booksInList(), pane);
        addButton(addBook(), pane);
        addButton(removeBook(), pane);
        addButton(saveLibrary(), pane);
        addButton(loadLibrary(), pane);
    }

    private void addButton(JButton button, Container container) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }

    private JButton displayLists() {
        displayListsButton.setText("Display lists");
        displayListsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(displayAllButton, bookApp.getLists());
            }
        });
        return displayListsButton;
    }

    private JButton displayAllBooks() {
        displayAllButton.setText("Display all books");
        displayAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                allBooksWindow();
            }
        });
        return displayAllButton;
    }

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

    private JButton booksInList() {
        displayBooksButton.setText("Display books in list");
        displayBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectList();
            }
        });
        return displayBooksButton;
    }

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

    private JButton addBook() {
        addBookButton.setText("Add book to list");
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(addBookButton, addBookToList());
            }
        });
        return addBookButton;
    }

    private String addBookToList() {
        String message = "";
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        createLabels(panel, c);
        createTextFields(panel, c);
        String genre = selectGenre(panel, c);
        String list = selectList(panel, c);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Book to List",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String choice = "";
        switch (result) {
            case JOptionPane.OK_OPTION:
                String[] userAnswers = getTextFields(panel, c);
                processInputs(userAnswers, genre, list);
                break;
        }
        return "Book has been added to " + choice + "!";
    }

    private void createLabels(JPanel panel, GridBagConstraints c) {
        JLabel label;

        label = new JLabel("Title: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(label, c);

        label = new JLabel("Author: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(label, c);

        label = new JLabel("Book x in series: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(label, c);
    }

    private void createTextFields(JPanel panel, GridBagConstraints c) {
        JTextField titleField;
        JTextField authorField;
        JTextField seriesNumField;

        titleField = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(titleField, c);

        authorField = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(authorField, c);

        seriesNumField = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(seriesNumField, c);
    }

    private String[] getTextFields(JPanel panel, GridBagConstraints c) {
        String[] userAnswer = new String[3];

        JTextField titleField = (JTextField) panel.getComponentAt(1, 0);
        JTextField authorField;
        JTextField seriesNumField;

        userAnswer[0] = titleField.getText();

        System.out.println(userAnswer[0]);
        System.out.println(userAnswer[1]);
        System.out.println("2: " + userAnswer[2]);
        return userAnswer;
    }

    private String selectList(JPanel panel, GridBagConstraints c) {
        JLabel label = new JLabel("Please select a list:  ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
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

        String choice = "" + comboBox.getSelectedItem();

        return choice;
    }

    private void processInputs(String[] bookInfo, String genre, String list) {
        String title = bookInfo[0];
        String author = bookInfo[1];
        int seriesNum = Integer.parseInt(bookInfo[2]);
        bookApp.addToList(title, author, seriesNum, genre, list);
    }

    private JButton removeBook() {
        removeBookButton.setText("Remove book from list");
        removeBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(removeBookButton, "Remove book");
            }
        });
        return removeBookButton;
    }

    private JButton saveLibrary() {
        saveLibButton.setText("Save library");
        saveLibButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(saveLibButton, bookApp.saveLibrary());
            }
        });
        return saveLibButton;
    }

    private JButton loadLibrary() {
        loadLibButton.setText("Load library");
        loadLibButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(loadLibButton, bookApp.loadLibrary());
            }
        });
        return loadLibButton;
    }

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

    private JFrame createWindow(String title) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

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
