package cn.element.lambda.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TimeAspect {
    
    public static final Logger log = LoggerFactory.getLogger(TimeAspect.class);

    @Before("execution(* cn.element.lambda.aspectj.HelloWorld.*(..))")
    public void before() {
        log.debug("前置通知开始计时");
    }

    @After("execution(* cn.element.lambda.aspectj.HelloWorld.*(..))")
    public void after() {
        log.debug("后置通知开始计时");
    }
}
