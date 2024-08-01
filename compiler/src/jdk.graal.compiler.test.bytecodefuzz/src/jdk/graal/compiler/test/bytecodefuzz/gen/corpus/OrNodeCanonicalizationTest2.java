package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.StructuredGraph;
import org.junit.Test;
public class OrNodeCanonicalizationTest2 {
    private static int x = 42;

    public static int orSelfNegationRightIntSnippet()  {
        return x | ~x;
    }
}