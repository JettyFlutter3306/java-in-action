package cn.element.base;

import org.junit.Test;

public class TestBase {
    
    @Test
    public void testByte() {
        byte a = (byte) 300;
        
        // 100101100
        System.out.println(Integer.toBinaryString(300));

        int b = 0b00101100;
        System.out.println(b);
        System.out.println(a);
    }
    
    @Test
    public void testCodePoint() {
        String a = "Hello";
        int n = a.codePointCount(0, a.length());
        System.out.println(n);
    }

}
