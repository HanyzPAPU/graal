package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.graph.Node;
import jdk.graal.compiler.nodes.IfNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.AddNode;
import jdk.graal.compiler.nodes.calc.FloatEqualsNode;
import jdk.graal.compiler.nodes.calc.LeftShiftNode;
import jdk.graal.compiler.nodes.calc.ReinterpretNode;
import org.junit.Assert;
import org.junit.Test;
public class CanonicalizedConversionTest1 {
    private static float value = -567.890F;

    public static int snippet4()  {
        return Float.floatToIntBits(value) + Float.floatToIntBits(value) + Float.floatToIntBits(value);
    }
}