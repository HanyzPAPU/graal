package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.graph.iterators.NodeIterable;
import jdk.graal.compiler.nodes.LoopBeginNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.cfg.ControlFlowGraph;
import org.junit.Assert;
import org.junit.Test;
public class IterationDirectiveTest0 {
    public static int arg = 7;

    public static int loopFrequencySnippet()  {
        int x = arg;
        while (x > 1) {
            GraalDirectives.controlFlowAnchor(); // prevent loop peeling or unrolling
            if (x % 2 == 0) {
                x /= 2;
            } else {
                x = 3 * x + 1;
            }
        }
        return x;
    }
}