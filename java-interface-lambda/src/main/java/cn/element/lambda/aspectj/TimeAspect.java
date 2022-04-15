package cn.element.lambda.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TimeAspect {

    @Before("execution(* cn.element.lambda.aspectj.HelloWorld.sayHello())")
    public void before() {
        System.out.println("前置通知开始计时");
    }

    @After("execution(* cn.element.lambda.aspectj.HelloWorld.sayHello())")
    public void after() {
        System.out.println("后置通知开始计时");
    }
}
