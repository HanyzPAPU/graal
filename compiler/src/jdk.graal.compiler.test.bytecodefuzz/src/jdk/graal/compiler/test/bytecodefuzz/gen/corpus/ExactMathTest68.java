package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.calc.RoundNode;
import jdk.graal.compiler.nodes.graphbuilderconf.InvocationPlugins;
import jdk.graal.compiler.truffle.substitutions.TruffleGraphBuilderPlugins;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import com.oracle.truffle.api.ExactMath;
import jdk.vm.ci.amd64.AMD64;
public class ExactMathTest68 {
    public static float a = Float.NaN;

    public static float truncateFloat()  {
        return ExactMath.truncate(a);
    }
}