package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem10 {
    private static int a = -100000;
    private static int b = 3000000;

    public static long test4()  {
        int ra = Math.abs(a % b);
        int rb = Math.abs(a) % b;
        return ra << 32 | rb;
    }
}