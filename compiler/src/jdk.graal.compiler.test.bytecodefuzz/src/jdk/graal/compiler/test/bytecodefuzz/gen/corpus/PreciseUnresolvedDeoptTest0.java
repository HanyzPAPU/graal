package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Assert;
import org.junit.Test;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.DeoptimizeNode;
import jdk.graal.compiler.nodes.IfNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.extended.StateSplitProxyNode;
public class PreciseUnresolvedDeoptTest0 {
    private static boolean condition = true;

    public static boolean doNotConvertToGuardSnippet()  {
        if (condition) {
            GraalDirectives.preciseDeoptimize();
        }
        return condition;
    }
}