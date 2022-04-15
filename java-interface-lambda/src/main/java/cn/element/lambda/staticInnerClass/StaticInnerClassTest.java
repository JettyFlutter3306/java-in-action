package cn.element.lambda.staticInnerClass;

import java.util.Arrays;

public class StaticInnerClassTest {

    public static void main(String[] args) {
        double[] d = new double[20];

        for (int i = 0; i < d.length; i++) {
            d[i] = Math.floor(10000 * Math.random()) / 100;
        }

        System.out.println(Arrays.toString(d));
        
        ArrayAlg.Pair p = ArrayAlg.minmax(d);
        
        System.out.println("min = " + p.getFirst());
        System.out.println("max = " + p.getSecond());
    }

}
