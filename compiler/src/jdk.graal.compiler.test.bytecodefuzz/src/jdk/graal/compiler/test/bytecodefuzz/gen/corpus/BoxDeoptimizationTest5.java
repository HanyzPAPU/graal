package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Assert;
import org.junit.Test;
public class BoxDeoptimizationTest5 {
    

    public static void testBooleanSnippet()  {
        Object[] values = {true, false, new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Boolean.valueOf(true));
        Assert.assertSame(values[1], Boolean.valueOf(false));
    }
}