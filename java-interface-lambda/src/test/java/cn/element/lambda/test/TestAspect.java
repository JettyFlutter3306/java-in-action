package cn.element.lambda.test;

import cn.element.lambda.aspectj.HelloWorld;
import org.junit.Test;

public class TestAspect {
    
    @Test
    public void test() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }

}
