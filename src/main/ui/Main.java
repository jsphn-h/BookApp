package ui;


import model.Book;

import java.io.FileNotFoundException;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookAppGUI bookAppGUI = new BookAppGUI();
                    bookAppGUI.createAndShowGUI();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
//        new BookApp();
    }
}
