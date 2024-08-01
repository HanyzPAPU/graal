package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import jdk.graal.compiler.debug.DebugContext;
import jdk.graal.compiler.debug.TTY;
import jdk.graal.compiler.graph.Node;
import jdk.graal.compiler.nodeinfo.Verbosity;
import jdk.graal.compiler.nodes.AbstractMergeNode;
import jdk.graal.compiler.nodes.PhiNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.StructuredGraph.AllowAssumptions;
import jdk.graal.compiler.nodes.StructuredGraph.ScheduleResult;
import jdk.graal.compiler.nodes.cfg.HIRBlock;
import jdk.graal.compiler.nodes.java.InstanceOfNode;
import jdk.graal.compiler.phases.common.CanonicalizerPhase;
import jdk.graal.compiler.phases.common.ConditionalEliminationPhase;
import jdk.graal.compiler.phases.schedule.SchedulePhase;
public class TypeSystemTest1 {
    private static Object o = "referenceSnippet3";

    public static int test4Snippet()  {
        if (o == null) {
            Object o2 = Integer.class;
            if (o == o2) {
                return 3;
            } else {
                return 1;
            }
        } else {
            return 2;
        }
    }
}