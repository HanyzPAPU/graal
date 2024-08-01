package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.StructuredGraph;
import org.junit.Test;
public class AndNodeCanonicalizationTest3 {
    private static long x = 42L;

    public static long andSelfNegationLeftLongSnippet()  {
        return ~x & x;
    }
}