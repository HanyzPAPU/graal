package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.util.Arrays;
import jdk.graal.compiler.code.CompilationResult;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.ConstantNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.StructuredGraph.AllowAssumptions;
import org.junit.Test;
import jdk.vm.ci.code.InstalledCode;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class FloatArraysEqualsTest6 {
    

    public static boolean testDoubleArrayWithPEASnippet1()  {
        return Arrays.equals(new double[]{Double.longBitsToDouble(0x7ff8000000000000L)}, new double[]{Double.longBitsToDouble(0x7ff8000000000000L)});
    }
}