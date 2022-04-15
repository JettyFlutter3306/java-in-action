package cn.element.lambda.anonymousInnerClass;

import javax.swing.*;

public class AnonymousInnerClassTest {

    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock();
        clock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Quit Program?");
        System.exit(0);
    }

}
