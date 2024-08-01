package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class BC_newarray2 {
    private static int a = 1;

    public static int test()  {
        if (new boolean[a] == null) {
            return -1;
        }
        if (new char[a] == null) {
            return -1;
        }
        if (new float[a] == null) {
            return -1;
        }
        if (new double[a] == null) {
            return -1;
        }
        if (new byte[a] == null) {
            return -1;
        }
        if (new short[a] == null) {
            return -1;
        }
        if (new int[a] == null) {
            return -1;
        }
        if (new long[a] == null) {
            return -1;
        }

        return a;
    }
}