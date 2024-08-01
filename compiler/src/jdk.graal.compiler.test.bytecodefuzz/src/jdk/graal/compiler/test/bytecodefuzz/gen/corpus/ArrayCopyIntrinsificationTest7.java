package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.graph.Node;
import jdk.graal.compiler.nodes.DirectCallTargetNode;
import jdk.graal.compiler.nodes.Invoke;
import jdk.graal.compiler.nodes.LoweredCallTargetNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.options.OptionValues;
import jdk.graal.compiler.replacements.arraycopy.ArrayCopySnippets;
import org.junit.Assert;
import org.junit.Test;
import jdk.vm.ci.code.InstalledCode;
import jdk.vm.ci.meta.JavaMethod;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class ArrayCopyIntrinsificationTest7 {
    public static Object src = new byte[128];
    public static int srcPos = 4;
    public static Object dst = null;
    public static int dstPos = 2;
    public static int length = 3;

    public static Object genericArraycopyCatchNullException()  {
        boolean caught = false;
        try {
            System.arraycopy(src, srcPos, dst, dstPos, length);
        } catch (NullPointerException e) {
            caught = true;
        }
        return caught;
    }
}