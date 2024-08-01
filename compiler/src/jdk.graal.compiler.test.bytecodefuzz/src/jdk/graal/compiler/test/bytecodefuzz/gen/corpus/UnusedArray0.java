package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import static jdk.graal.compiler.graph.test.matchers.NodeIterableIsEmpty.isEmpty;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import jdk.graal.compiler.graph.iterators.NodeIterable;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.java.NewArrayNode;
public class UnusedArray0 {
    

    public void smallArray()  {
        byte[] array = new byte[3];
    }
}