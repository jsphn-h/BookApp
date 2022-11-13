package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookAppGUI extends JPanel implements ActionListener {
    protected JButton displayAll;
    protected JButton displayList;

    public BookAppGUI() {
        displayAll = new JButton("Display all books");
    }

    public void actionPerformed(ActionEvent e) {

    }

    public static void createAndShowGUI() {
        //Create and set up the window
        JFrame frame = new JFrame("BookApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane
        BookAppGUI newContentPane = new BookAppGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //Display the window
        frame.pack();
        frame.setVisible(true);
    }
}
