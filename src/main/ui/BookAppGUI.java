package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookAppGUI extends JFrame {
    private static JButton displayAllButton = new JButton();
    private static JButton displayListsButton = new JButton();
    private static JButton displayBooksButton = new JButton();
    private static JButton addBookButton = new JButton();
    private static JButton removeBookButton = new JButton();
    private static JButton saveLibButton = new JButton();
    private static JButton loadLibButton = new JButton();


    public static void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addButton(displayAllBooks(), pane);
        addButton(displayLists(), pane);
        addButton(booksInList(), pane);
        addButton(addBook(), pane);
        addButton(removeBook(), pane);
        addButton(saveLibrary(), pane);
        addButton(loadLibrary(), pane);
    }

    private static void addButton(JButton button, Container container) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }

    private static JButton displayLists() {
        displayListsButton.setText("Display lists");
        displayListsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(displayAllButton, "Lists");
            }
        });
        return displayListsButton;
    }

    private static JButton displayAllBooks() {
        displayAllButton.setText("Display all books");
        displayAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(displayAllButton, "Hello World!");
            }
        });
        return displayAllButton;
    }

    private static JButton booksInList() {
        displayBooksButton.setText("Display books in list books");
        return displayBooksButton;
    }

    private static JButton addBook() {
        addBookButton.setText("Add book to list");
        return addBookButton;
    }

    private static JButton removeBook() {
        removeBookButton.setText("Remove book from list");
        return removeBookButton;
    }

    private static JButton saveLibrary() {
        saveLibButton.setText("Save library");
        return saveLibButton;
    }

    private static JButton loadLibrary() {
        loadLibButton.setText("Load library");
        return loadLibButton;
    }

    public static void createAndShowGUI() {
        // Create window
        JFrame frame = new JFrame("BookApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up content pane
        addComponentsToPane(frame.getContentPane());

        // Display window
        frame.pack();
        frame.setVisible(true);
    }
}
