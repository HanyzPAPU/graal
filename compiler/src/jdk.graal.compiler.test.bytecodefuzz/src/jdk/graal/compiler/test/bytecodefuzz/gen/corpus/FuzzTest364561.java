package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;

public class FuzzTest364561 {
    public static int param4 = 0;

    public static boolean method1()  {
        // Checkstyle: stop
        return (short) '㱆' > (short) ((char) Integer.compare(param4, 2147483646));
        // Checkstyle: start
    }
}