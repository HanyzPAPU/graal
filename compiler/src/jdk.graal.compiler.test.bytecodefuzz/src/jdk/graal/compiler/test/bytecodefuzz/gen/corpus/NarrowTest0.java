package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class NarrowTest0 {
    private static char c0 = (char) 0;

    static boolean snippet0()  {
        return -2 > (byte) ((byte) c0 / (byte) 2134864769);
    }
}