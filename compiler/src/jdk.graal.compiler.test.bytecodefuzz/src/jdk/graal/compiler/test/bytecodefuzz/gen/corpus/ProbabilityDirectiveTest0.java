package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.graph.iterators.NodeIterable;
import jdk.graal.compiler.nodes.AbstractBeginNode;
import jdk.graal.compiler.nodes.IfNode;
import jdk.graal.compiler.nodes.ReturnNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.debug.ControlFlowAnchorNode;
import org.junit.Assert;
import org.junit.Test;
public class ProbabilityDirectiveTest0 {
    private static int arg = 5;

    public static int branchProbabilitySnippet()  {
        if (arg > 0) {
            GraalDirectives.controlFlowAnchor(); // prevent removal of the if
            return 1;
        } else {
            GraalDirectives.controlFlowAnchor(); // prevent removal of the if
            return 2;
        }
    }
}