package ui;


import model.Book;

import java.io.FileNotFoundException;
import java.io.IOException;
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
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
//        new BookApp();
    }
}
