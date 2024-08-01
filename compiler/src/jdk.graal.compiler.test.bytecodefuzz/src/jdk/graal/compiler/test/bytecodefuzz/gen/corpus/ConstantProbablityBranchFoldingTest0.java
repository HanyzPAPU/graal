package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.graph.iterators.NodeIterable;
import jdk.graal.compiler.nodes.IfNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import org.junit.Assert;
import org.junit.Test;
public class ConstantProbablityBranchFoldingTest0 {
    

    public static int branchFoldingSnippet1()  {
        if (GraalDirectives.injectBranchProbability(0.5, true)) {
            return 1;
        } else {
            return 2;
        }
    }
}