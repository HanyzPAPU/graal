package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.graph.Node;
import jdk.graal.compiler.graph.iterators.NodeIterable;
import jdk.graal.compiler.nodes.IfNode;
import jdk.graal.compiler.nodes.LoopBeginNode;
import jdk.graal.compiler.nodes.ReturnNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.StructuredGraph.AllowAssumptions;
import jdk.graal.compiler.nodes.debug.ControlFlowAnchorNode;
import jdk.graal.compiler.phases.OptimisticOptimizations;
import org.junit.Assert;
import org.junit.Test;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class ControlFlowAnchorDirectiveTest2 {
    public static int arg = 42;

    public static int verifyDuplicateSnippet()  {
        int ret;
        if (arg > 5) {
            ret = 17;
        } else {
            ret = arg;
        }
        return 42 / ret;
    }
}