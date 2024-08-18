package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class SubCanonicalizationTest3 {
    public static int a = 0xFFFF0000;
    public static int b = 0x0000FFFF;

    public static int snippet0()  {
        return (a | b) - (a ^ b);
    }
}