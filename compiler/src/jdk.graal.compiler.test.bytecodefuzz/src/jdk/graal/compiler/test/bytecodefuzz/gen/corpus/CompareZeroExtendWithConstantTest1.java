package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class CompareZeroExtendWithConstantTest1 {
    public static boolean p0 = true;
    public static long p1 = 53069L;

    public static boolean snippet02()  {
        boolean var0 = p0;
        byte b = (byte) p1;
        for (long i = 245799965; i >= 245797839; i = 3) {
            b = (byte) Character.toUpperCase((char) b);
        }
        return var0;
    }
}