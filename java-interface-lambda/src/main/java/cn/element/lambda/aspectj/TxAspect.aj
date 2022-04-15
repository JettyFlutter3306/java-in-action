package cn.element.lambda.aspectj;

/**
 * 上面的 TxAspect 根本不是一个 Java 类，所以 aspect 也不是 Java 支持的关键字，
 * 它只是 AspectJ 才能识别的关键字。 其后缀为.aj，该文件的完整文件名为TxAspect.aj
 * 切面的语法只有AspectJ可以识别，并使用其特殊的编译器ajc来编译
 * 这段代码拦截Hello.sayHello()方法，并在其执行之前开始事务，
 * proceed()方法代表回调原来的sayHello()方法，执行结束后结束事务。
 */
public aspect TxAspect {

//    void around(): call(void HelloWorld.sayHello()) {
//        System.out.println("开始事务。。。");
//        proceed();
//        System.out.println("结束事务。。。");
//    }
}
