package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.StructuredGraph;
import org.junit.Test;
public class AndNodeCanonicalizationTest0 {
    public static int x = 23;
    public static int y = 42;

    public static int andDeMorganSnippet()  {
        return ~x & ~y;
    }
}