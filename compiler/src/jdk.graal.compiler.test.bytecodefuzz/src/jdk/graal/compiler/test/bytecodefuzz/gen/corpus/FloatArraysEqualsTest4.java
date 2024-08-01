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
public class FloatArraysEqualsTest4 {
    

    public static boolean testFloatArrayWithPEASnippet2()  {
        return Arrays.equals(new float[]{Float.intBitsToFloat(0x7fc00000)}, new float[]{Float.intBitsToFloat(0x7f800001)});

    }
}