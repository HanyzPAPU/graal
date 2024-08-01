package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Assert;
import org.junit.Test;
public class BoxDeoptimizationTest1 {
    

    public static void testLongSnippet()  {
        long highBitsOnly = 2L << 40;
        Object[] values = {42L, -42L, highBitsOnly, new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Long.valueOf(42));
        Assert.assertSame(values[1], Long.valueOf(-42));
        Assert.assertNotSame(values[2], highBitsOnly);
    }
}