package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.StructuredGraph;
import org.junit.Test;
public class XorNodeCanonicalizationTest0 {
    private static int x = 23;
    private static int y = 42;

    public static int xorDeMorganSnippet()  {
        return ~x ^ ~y;
    }
}