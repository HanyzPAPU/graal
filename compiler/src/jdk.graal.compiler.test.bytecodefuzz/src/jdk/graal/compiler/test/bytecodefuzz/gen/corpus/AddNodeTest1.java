package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.graph.Node;
import jdk.graal.compiler.graph.NodeClass;
import jdk.graal.compiler.nodes.ConstantNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.AddNode;
import org.junit.Assert;
import org.junit.Test;
public class AddNodeTest1 {
    public static int x = Integer.MAX_VALUE;

    public int addNotLeftSnippet()  {
        return ~x + x;
    }
}