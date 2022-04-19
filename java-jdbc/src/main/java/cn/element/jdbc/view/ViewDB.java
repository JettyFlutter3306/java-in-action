package cn.element.jdbc.view;

import java.awt.*;
import javax.swing.*;

/**
 * This program uses metadata to display arbitrary tables in a database.
 */
public class ViewDB {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ViewDBFrame();
            frame.setTitle("ViewDB");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

