package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Assert;
import org.junit.Test;
public class BoxDeoptimizationTest4 {
    

    public static void testByteSnippet()  {
        Object[] values = {(byte) 42, (byte) -42, new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Byte.valueOf((byte) 42));
        Assert.assertSame(values[1], Byte.valueOf((byte) -42));
    }
}