package cn.element.lambda.interfaces;

public interface Eatable {
    
    private static void run() {
        System.out.println("跑步");
    }
    
    private void look() {
        System.out.println("看");
    }
    
    private void hit() {
        look();
    }
    
    default void eat() {
        run();
        look();
        shout();
        System.out.println("吃");
    }
    
    static void shout() {
        run();
    }
    
}
