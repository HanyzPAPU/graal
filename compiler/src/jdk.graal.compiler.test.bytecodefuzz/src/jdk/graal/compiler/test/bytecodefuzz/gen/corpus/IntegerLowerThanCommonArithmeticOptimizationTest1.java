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
public class IntegerLowerThanCommonArithmeticOptimizationTest1 {
    public static int x = 2;
    public static int y = 3;
    public static int c = 4;
    public static int d = 5;

    public static boolean testSnippetSignExtend()  {
        long xL = x;
        long yL = y;
        if ((xL << c) + d < (yL << c) + d) {
            GraalDirectives.controlFlowAnchor();
            return true;
        } else {
            return false;
        }
    }
}