package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_newarray0 {
    public static int a = 0;

    public static int test()  {
        if (new boolean[3] == null) {
            return -1;
        }
        if (new char[3] == null) {
            return -1;
        }
        if (new float[3] == null) {
            return -1;
        }
        if (new double[3] == null) {
            return -1;
        }
        if (new byte[3] == null) {
            return -1;
        }
        if (new short[3] == null) {
            return -1;
        }
        if (new int[3] == null) {
            return -1;
        }
        if (new long[3] == null) {
            return -1;
        }

        return a;
    }
}