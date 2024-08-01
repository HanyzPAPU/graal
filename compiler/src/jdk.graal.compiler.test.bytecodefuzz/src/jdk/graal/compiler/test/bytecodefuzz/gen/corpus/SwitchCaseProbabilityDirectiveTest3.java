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
public class SwitchCaseProbabilityDirectiveTest3 {
    public static int x = 99;

    public static int zeroProbabilityDeoptimize()  {
        /*
         * Total probability across branches should add up to 1, and if it doesn't an error should
         * be thrown.
         */
        switch (x) {
            case 100:
                return 10;
            case 200:
                return 20;
            case 300:
                return 30;
            case 400:
                return 40;
            default: {
                GraalDirectives.deoptimize();
                throw GraalError.shouldNotReachHere("Deoptimize");
            }
        }
    }
}