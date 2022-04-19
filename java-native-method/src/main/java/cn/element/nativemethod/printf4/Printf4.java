package cn.element.nativemethod.printf4;

import java.io.*;

public class Printf4 {
    public static native void fprint(PrintWriter ps, String format, double x);

    static {
        System.loadLibrary("Printf4");
    }
}
