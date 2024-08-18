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
public class BciBlockMappingTest1 {
    public static int n = 4;
    public static int increment = 1;

    public static int async2()  {
        int x = 42;

        if (increment == 4) {
            x = 41;
            for (int i = 0; i < n; i += increment) {
            }
        }

        try {
            for (int i = 0; i < n; i += increment) {
            }
            return -1;

        } catch (Throwable ex) {
            return x;
        }
    }
}