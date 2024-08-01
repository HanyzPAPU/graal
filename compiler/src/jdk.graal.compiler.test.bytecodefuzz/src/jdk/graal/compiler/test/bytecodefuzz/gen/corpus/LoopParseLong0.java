package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LoopParseLong0 {
    private static String s = "7";
    private static int radix = 10;

    public static long testShortened()  throws NumberFormatException {
        long result = 0;
        boolean negative = false;
        int len = s.length();
        char firstChar = s.charAt(0);
        if (firstChar < '0') {
            if (firstChar == '-') {
                negative = true;
            } else if (firstChar != '+') {
                throw new NumberFormatException();
            }
            if (len == 1) {
                throw new NumberFormatException();
            }
        }
        return result;
    }
}