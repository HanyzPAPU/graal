package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.util.HashMap;
import jdk.graal.compiler.nodes.IfNode;
import jdk.graal.compiler.nodes.Invoke;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.AbsNode;
import jdk.graal.compiler.nodes.calc.ReinterpretNode;
import jdk.graal.compiler.replacements.nodes.BitCountNode;
import jdk.graal.compiler.replacements.nodes.BitScanForwardNode;
import jdk.graal.compiler.replacements.nodes.BitScanReverseNode;
import jdk.graal.compiler.replacements.nodes.CountLeadingZerosNode;
import jdk.graal.compiler.replacements.nodes.CountTrailingZerosNode;
import jdk.graal.compiler.replacements.nodes.ReverseBytesNode;
import org.junit.Test;
import jdk.vm.ci.code.InstalledCode;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class StandardMethodSubstitutionsTest25 {
    private static boolean cond = true;
    private static Object object = new HashMap<>();

    public static boolean isInstance2()  {
        Class<?> clazz;
        if (cond) {
            clazz = String.class;
        } else {
            clazz = java.util.HashMap.class;
        }
        return clazz.isInstance(object);
    }
}