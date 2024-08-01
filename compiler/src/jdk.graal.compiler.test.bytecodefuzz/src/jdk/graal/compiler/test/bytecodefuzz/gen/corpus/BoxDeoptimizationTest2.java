package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Assert;
import org.junit.Test;
public class BoxDeoptimizationTest2 {
    

    public static void testCharSnippet()  {
        Object[] values = {'a', 'Z', new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Character.valueOf('a'));
        Assert.assertSame(values[1], Character.valueOf('Z'));
    }
}