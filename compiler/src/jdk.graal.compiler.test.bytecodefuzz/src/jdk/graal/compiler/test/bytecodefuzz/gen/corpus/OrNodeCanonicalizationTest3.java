package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.StructuredGraph;
import org.junit.Test;
public class OrNodeCanonicalizationTest3 {
    public static long x = 42L;

    public static long orSelfNegationLeftLongSnippet()  {
        return ~x | x;
    }
}