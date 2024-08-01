package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
import jdk.graal.compiler.api.directives.GraalDirectives;
public class BC_iadd_const33 {
    private static long a = (long) 42;
    private static long b = (long) -1;
    private static boolean neg = true;

    public static long test()  {
        long x = GraalDirectives.opaque(a);
        if (!neg) {
            return x + b;
        }
        return x - b;
    }
}