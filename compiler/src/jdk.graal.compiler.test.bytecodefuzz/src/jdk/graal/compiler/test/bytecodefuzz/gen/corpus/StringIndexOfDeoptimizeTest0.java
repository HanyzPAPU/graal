package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class StringIndexOfDeoptimizeTest0 {
    private static String a = "a";

    public int testStringIndexOfDeoptimize()  {
        int result = a.indexOf("ba");
        if (result == -1) {
            GraalDirectives.deoptimizeAndInvalidate();
        }
        return result;
    }
}