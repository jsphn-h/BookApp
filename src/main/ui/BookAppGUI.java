package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookAppGUI extends JFrame {
    private static JButton displayAll = new JButton();
    protected static JButton displayList = new JButton();

    public static void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addButton(displayAllBooks(), pane);
        addButton(displayList(), pane);

    }

    private static void addButton(JButton button, Container container) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }

    private static JButton displayAllBooks() {
        displayAll.setText("Display all books");
//        displayAll.setBounds(40, 40, 100, 30);
        displayAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(displayAll, "Hello World!");
            }
        });
        return displayAll;
    }

    private static JButton displayList() {
        displayList.setText("Display list");
//        displayAll.setBounds(40, 60, 100, 30);
        displayList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(displayAll, "Books in list");
            }
        });
        return displayList;
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
