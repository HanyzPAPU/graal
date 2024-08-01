package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.common.type.IntegerStamp;
import jdk.graal.compiler.debug.GraalError;
import jdk.graal.compiler.graph.Node;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.AddNode;
import jdk.graal.compiler.nodes.calc.LeftShiftNode;
import jdk.graal.compiler.nodes.calc.SignExtendNode;
import jdk.graal.compiler.nodes.calc.ZeroExtendNode;
import org.junit.Assert;
import org.junit.Test;
public class IntegerLowerThanCommonArithmeticOptimizationTest0 {
    private static int x = 2;
    private static int y = 3;
    private static int c = 4;
    private static int d = 5;

    public static boolean testSnippet()  {
        if ((x << c) + d < (y << c) + d) {
            GraalDirectives.controlFlowAnchor();
            return true;
        } else {
            return false;
        }
    }
}