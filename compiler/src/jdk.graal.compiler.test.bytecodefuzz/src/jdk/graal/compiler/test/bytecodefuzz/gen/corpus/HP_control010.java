package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class HP_control010 {
    private static int count = 40;

    public static int test()  {
        int i1 = 1;
        int i2 = 2;
        int i3 = 3;
        int i4 = 4;

        for (int i = 0; i < count; i++) {
            i1 = i2;
            i2 = i3;
            i3 = i4;
            i4 = i1;

            i1 = i2;
            i2 = i3;
            i3 = i4;
            i4 = i1;

            i1 = i2;
            i2 = i3;
            i3 = i4;
            i4 = i1;

            i1 = i2;
            i2 = i3;
            i3 = i4;
            i4 = i1;
        }

        return i1 + i2 * 10 + i3 * 100 + i4 * 1000;
    }
}