package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
import jdk.graal.compiler.api.directives.GraalDirectives;
public class BC_iadd_const23 {
    private static short a = (short) 42;
    private static short b = (short) -1;
    private static boolean neg = true;

    public static int test()  {
        short x = GraalDirectives.opaque(a);
        if (!neg) {
            return x + b;
        }
        return x - b;
    }
}