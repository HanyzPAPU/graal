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
public class SwitchCaseProbabilityDirectiveTest1 {
    private static int x = 4;

    public static int keyHoleSwitchSnippet()  {
        switch (x) {
            case 3:
                GraalDirectives.injectSwitchCaseProbability(0.2);
                return 10;
            case 4:
                GraalDirectives.injectSwitchCaseProbability(0.2);
                return 20;
            case 6:
                GraalDirectives.injectSwitchCaseProbability(0.2);
                return 30;
            case 7:
                GraalDirectives.injectSwitchCaseProbability(0.2);
                return 40;
            default:
                GraalDirectives.injectSwitchCaseProbability(0.2);
                return 42;
        }
    }
}