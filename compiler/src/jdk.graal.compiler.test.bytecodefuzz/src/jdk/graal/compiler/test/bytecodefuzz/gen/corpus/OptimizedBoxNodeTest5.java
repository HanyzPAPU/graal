package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.util.ArrayList;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.loop.phases.LoopFullUnrollPhase;
import jdk.graal.compiler.nodes.IfNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.StructuredGraph.AllowAssumptions;
import jdk.graal.compiler.nodes.extended.BoxNode;
import jdk.graal.compiler.nodes.loop.DefaultLoopPolicies;
import jdk.graal.compiler.options.OptionValues;
import jdk.graal.compiler.phases.common.BoxNodeIdentityPhase;
import jdk.graal.compiler.phases.common.BoxNodeOptimizationPhase;
import jdk.graal.compiler.phases.common.CanonicalizerPhase;
import jdk.graal.compiler.phases.common.DisableOverflownCountedLoopsPhase;
import jdk.graal.compiler.phases.util.GraphOrder;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import jdk.vm.ci.code.InstalledCode;
import jdk.vm.ci.code.InvalidInstalledCodeException;
public class OptimizedBoxNodeTest5 {
    

    public static long minLongCacheValue2()  {
        long longCacheMinValue = 0;
        while (true) {
            if (Long.valueOf(longCacheMinValue - 1) != Long.valueOf(longCacheMinValue - 1)) {
                return longCacheMinValue;
            }
            longCacheMinValue -= 1;
        }
    }
}