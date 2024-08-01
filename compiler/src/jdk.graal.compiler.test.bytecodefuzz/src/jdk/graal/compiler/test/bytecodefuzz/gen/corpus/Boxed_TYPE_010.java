package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Boxed_TYPE_010 {
    public static int i = 0;

    public static String test()  {
        if (i == 0) {
            return Boolean.TYPE.getName();
        }
        if (i == 1) {
            return Byte.TYPE.getName();
        }
        if (i == 2) {
            return Character.TYPE.getName();
        }
        if (i == 3) {
            return Double.TYPE.getName();
        }
        if (i == 4) {
            return Float.TYPE.getName();
        }
        if (i == 5) {
            return Integer.TYPE.getName();
        }
        if (i == 6) {
            return Long.TYPE.getName();
        }
        if (i == 7) {
            return Short.TYPE.getName();
        }
        if (i == 8) {
            return Void.TYPE.getName();
        }
        return null;
    }
}