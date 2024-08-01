package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
import jdk.graal.compiler.api.directives.GraalDirectives;
public class BC_iadd_const02 {
    private static int a = 42;
    private static int b = 1;
    private static boolean neg = true;

    public static int test()  {
        int x = GraalDirectives.opaque(a);
        if (!neg) {
            return x + b;
        }
        return x - b;
    }
}