package ui;


import java.io.FileNotFoundException;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        new BookApp();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BookAppGUI.createAndShowGUI();
            }
        });
    }
}
