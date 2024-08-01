package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.java.BciBlockMapping;
import jdk.graal.compiler.nodes.FrameState;
import jdk.graal.compiler.nodes.SafepointNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.StructuredGraph.AllowAssumptions;
import jdk.graal.compiler.nodes.ValueNode;
import jdk.graal.compiler.nodes.ValuePhiNode;
import org.junit.Test;
import java.util.Random;
public class BciBlockMappingTest0 {
    private static int n = 4;
    private static int increment = 1;

    static int async()  {
        int x = 42;

        try {
            for (int i = 0; i < n; i += increment) {
            }
            return -1;

        } catch (Throwable ex) {
            return x;
        }
    }
}