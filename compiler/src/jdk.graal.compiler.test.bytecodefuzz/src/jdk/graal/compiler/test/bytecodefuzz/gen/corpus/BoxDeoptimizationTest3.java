package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Assert;
import org.junit.Test;
public class BoxDeoptimizationTest3 {
    

    public static void testShortSnippet()  {
        Object[] values = {(short) 42, (short) -42, new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Short.valueOf((short) 42));
        Assert.assertSame(values[1], Short.valueOf((short) -42));
    }
}