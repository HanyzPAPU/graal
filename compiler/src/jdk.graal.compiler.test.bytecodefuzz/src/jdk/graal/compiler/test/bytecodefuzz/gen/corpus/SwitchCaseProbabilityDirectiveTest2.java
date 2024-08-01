package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Assert;
import org.junit.Test;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.debug.DebugOptions;
import jdk.graal.compiler.debug.GraalError;
import jdk.graal.compiler.graph.iterators.NodeIterable;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.extended.IntegerSwitchNode;
import jdk.graal.compiler.nodes.extended.SwitchCaseProbabilityNode;
import jdk.graal.compiler.options.OptionValues;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class SwitchCaseProbabilityDirectiveTest2 {
    private static int x = 200;

    public static int zeroProbabilityDeoptimize()  {
        /*
         * Total probability across branches should add up to 1, and if it doesn't an error should
         * be thrown.
         */
        switch (x) {
            case 100:
                GraalDirectives.injectSwitchCaseProbability(0.25);
                return 10;
            case 200:
                GraalDirectives.injectSwitchCaseProbability(0.25);
                return 20;
            case 300:
                GraalDirectives.injectSwitchCaseProbability(0.25);
                return 30;
            case 400:
                GraalDirectives.injectSwitchCaseProbability(0.25);
                return 40;
            default: {
                GraalDirectives.injectSwitchCaseProbability(0);
                GraalDirectives.deoptimize();
                throw GraalError.shouldNotReachHere("Deoptimize");
            }
        }
    }
}