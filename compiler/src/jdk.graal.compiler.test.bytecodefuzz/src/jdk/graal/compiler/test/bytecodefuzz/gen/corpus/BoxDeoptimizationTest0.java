package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Assert;
import org.junit.Test;
public class BoxDeoptimizationTest0 {
    

    public static void testIntegerSnippet()  {
        Object[] values = {42, -42, new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Integer.valueOf(42));
        Assert.assertSame(values[1], Integer.valueOf(-42));
    }
}