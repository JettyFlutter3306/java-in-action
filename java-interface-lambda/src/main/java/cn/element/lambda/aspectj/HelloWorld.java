package cn.element.lambda.aspectj;

public class HelloWorld {

    public void sayHello() {
        System.out.println("Hello AspectJ");
    }

    public static void main(String[] args) {
        HelloWorld hello = new HelloWorld();
        hello.sayHello();
    }
}
