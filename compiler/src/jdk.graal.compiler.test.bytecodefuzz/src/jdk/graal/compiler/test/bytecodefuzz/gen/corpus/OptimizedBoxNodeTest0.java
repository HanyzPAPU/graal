package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.util.ArrayList;
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
public class OptimizedBoxNodeTest0 {
    

    static int testPEASnippet()  {
        if (Integer.valueOf(1024) == Integer.valueOf(1024)) {
            return 0;
        }
        return 1;
    }
}