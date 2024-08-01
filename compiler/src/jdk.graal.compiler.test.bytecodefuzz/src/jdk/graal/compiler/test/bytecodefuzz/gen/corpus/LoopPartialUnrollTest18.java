package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.util.ListIterator;
import org.junit.Ignore;
import org.junit.Test;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.common.CompilationIdentifier;
import jdk.graal.compiler.core.common.GraalOptions;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.debug.DebugContext;
import jdk.graal.compiler.graph.iterators.NodeIterable;
import jdk.graal.compiler.loop.phases.LoopPartialUnrollPhase;
import jdk.graal.compiler.nodes.LoopBeginNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.loop.DefaultLoopPolicies;
import jdk.graal.compiler.nodes.loop.Loop;
import jdk.graal.compiler.nodes.loop.LoopFragmentInside;
import jdk.graal.compiler.nodes.loop.LoopsData;
import jdk.graal.compiler.options.OptionValues;
import jdk.graal.compiler.phases.BasePhase;
import jdk.graal.compiler.phases.OptimisticOptimizations;
import jdk.graal.compiler.phases.PhaseSuite;
import jdk.graal.compiler.phases.common.CanonicalizerPhase;
import jdk.graal.compiler.phases.common.ConditionalEliminationPhase;
import jdk.graal.compiler.phases.common.DeadCodeEliminationPhase;
import jdk.graal.compiler.phases.common.DeoptimizationGroupingPhase;
import jdk.graal.compiler.phases.common.FloatingReadPhase;
import jdk.graal.compiler.phases.common.FrameStateAssignmentPhase;
import jdk.graal.compiler.phases.common.GuardLoweringPhase;
import jdk.graal.compiler.phases.common.HighTierLoweringPhase;
import jdk.graal.compiler.phases.common.MidTierLoweringPhase;
import jdk.graal.compiler.phases.common.RemoveValueProxyPhase;
import jdk.graal.compiler.phases.tiers.MidTierContext;
import jdk.graal.compiler.phases.tiers.Suites;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class LoopPartialUnrollTest18 {
    public static short arg = 9;

    public static long deoptExitSnippet()  {
        long r = 1;
        int i = 0;
        while (true) {
            if (i >= arg) {
                GraalDirectives.deoptimizeAndInvalidate();
                GraalDirectives.sideEffect(i);
                if (i == 123) {
                    continue;
                }
                break;
            }
            r *= i;
            i++;
        }
        return r;
    }
}