package cn.element.lambda.innerClass;

import javax.swing.*;

public class InnerClassTest {

    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();

        JOptionPane.showMessageDialog(null, "QuitProgram?");
        System.exit(0);
    }

}
