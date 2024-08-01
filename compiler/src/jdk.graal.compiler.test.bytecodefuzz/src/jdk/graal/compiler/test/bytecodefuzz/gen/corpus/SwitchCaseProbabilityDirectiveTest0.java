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
public class SwitchCaseProbabilityDirectiveTest0 {
    private static int x = 1;

    public static int switchProbabilitySnippet1()  {
        switch (x) {
            case 0:
                GraalDirectives.injectSwitchCaseProbability(0.5);
                return 1;
            case 1:
                GraalDirectives.injectSwitchCaseProbability(0.3);
                return 3;
            case 2:
                GraalDirectives.injectSwitchCaseProbability(0.1);
                return x * 3;
            default:
                GraalDirectives.injectSwitchCaseProbability(0.1);
                return x + 5;
        }
    }
}