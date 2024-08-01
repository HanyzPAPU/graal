package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.core.test.TestPhase;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.ConditionalNode;
import jdk.graal.compiler.options.OptionValues;
import jdk.graal.compiler.phases.tiers.Suites;
import org.junit.Assert;
import org.junit.Test;
public class IfNodeCanonicalizationTest26 {
    private static int m = 1;
    private static int n = 2;
    private static int a = 2;
    private static int b = 4;

    public int integerEqualsCondMove()  {
        if (m == n) {
            return a;
        }
        return b;
    }
}