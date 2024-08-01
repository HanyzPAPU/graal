package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.loop.phases.LoopPeelingPhase;
import jdk.graal.compiler.nodes.ReturnNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.StructuredGraph.AllowAssumptions;
import jdk.graal.compiler.nodes.ValueNode;
import jdk.graal.compiler.nodes.loop.DefaultLoopPolicies;
import jdk.graal.compiler.phases.common.CanonicalizerPhase;
import jdk.graal.compiler.phases.common.DeadCodeEliminationPhase;
import jdk.graal.compiler.phases.common.DisableOverflownCountedLoopsPhase;
import jdk.graal.compiler.phases.tiers.HighTierContext;
import jdk.graal.compiler.virtual.phases.ea.PartialEscapePhase;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
public class BoxingEliminationTest0 {
    private static int a = 1;

    public static Integer materializeTest1Snippet()  {
        Integer v = a;

        if (v == a) {
            return v;
        } else {
            return null;
        }
    }
}