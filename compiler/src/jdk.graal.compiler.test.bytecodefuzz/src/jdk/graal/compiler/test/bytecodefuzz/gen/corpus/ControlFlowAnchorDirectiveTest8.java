package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import jdk.graal.compiler.api.directives.GraalDirectives;
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
public class ControlFlowAnchorDirectiveTest8 {
    private static int arg = 0;
    private static boolean flag = false;

    public static void preventUnswitchSnippet()  {
        int ret = arg;
        while (GraalDirectives.injectBranchProbability(0.9999, ret < 1000)) {
            if (GraalDirectives.injectBranchProbability(0.5, flag)) {
                GraalDirectives.controlFlowAnchor();
                ret++;
            } else {
                ret += 2;
            }
        }
    }
}