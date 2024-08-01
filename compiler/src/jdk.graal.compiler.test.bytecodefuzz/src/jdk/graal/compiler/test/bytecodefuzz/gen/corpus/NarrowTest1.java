package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class NarrowTest1 {
    public static long l0 = 50400L;

    static boolean snippet1()  {
        if ((char) ((byte) l0) >= Character.MAX_VALUE) {
            return true;
        }
        GraalDirectives.blackhole((byte) l0);
        return false;
    }
}