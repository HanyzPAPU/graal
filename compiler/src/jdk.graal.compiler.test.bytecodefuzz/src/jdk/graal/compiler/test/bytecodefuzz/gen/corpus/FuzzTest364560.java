package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;

public class FuzzTest364560 {
    private static int param4 = 0;

    static boolean method0()  {
        // Checkstyle: stop
        return '㱆' > (char) Integer.compare(param4, 2147483646);
        // Checkstyle: start
    }
}