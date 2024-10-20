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
public class ArrayCopyIntrinsificationTest9 {
    public static Object src = new int[128];
    public static int srcPos = 0;
    public static Object dst = new int[128];
    public static int dstPos = Integer.MAX_VALUE;
    public static int length = 1;

    public static Object genericArraycopyCatchArrayIndexException()  {
        boolean caught = false;
        try {
            System.arraycopy(src, srcPos, dst, dstPos, length);
        } catch (ArrayIndexOutOfBoundsException e) {
            caught = true;
        }
        return caught;
    }
}