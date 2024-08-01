package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class ExpandLogicTest0 {
    public static int bar = 12;

    public static int snippet01()  {

        boolean b1 = bar == 1;
        boolean b2 = bar == 2 || b1;
        boolean b3 = bar == 3 || b2;
        boolean b4 = b3 || b2;
        boolean b5 = bar == 4 || b4;
        boolean b6 = b4 || b5;
        boolean b7 = bar == 5 || b6;
        boolean b8 = b6 || b7;

        GraalDirectives.sideEffect(123);
        if (b8) {
            GraalDirectives.sideEffect(123);
            return 1;
        }
        GraalDirectives.sideEffect(bar);
        return 0;
    }
}