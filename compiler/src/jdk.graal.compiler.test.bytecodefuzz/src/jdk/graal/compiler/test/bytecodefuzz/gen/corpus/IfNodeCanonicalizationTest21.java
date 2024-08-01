package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.core.test.TestPhase;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.ConditionalNode;
import jdk.graal.compiler.options.OptionValues;
import jdk.graal.compiler.phases.tiers.Suites;
import org.junit.Assert;
import org.junit.Test;
public class IfNodeCanonicalizationTest21 {
    private static long m = 1L;
    private static long n = 2L;

    public long minLong()  {
        return Math.min(m, n);
    }
}