package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lor2 {
    public static long a = 31L;
    public static long b = 63L;

    public static long test()  {
        return a | b;
    }
}