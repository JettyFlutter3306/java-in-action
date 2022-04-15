package cn.element.lambda.timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimerTest {
    
    public static class TimePrinter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("At the tone, the time is " + new Date());
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    public static void main(String[] args) {
        ActionListener listener = new TimePrinter();

        Timer t = new Timer(3000, listener);
        t.start();
        
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
