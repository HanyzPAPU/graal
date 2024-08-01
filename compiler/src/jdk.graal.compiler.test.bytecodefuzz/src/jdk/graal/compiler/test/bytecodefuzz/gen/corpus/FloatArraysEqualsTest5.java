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
public class FloatArraysEqualsTest5 {
    

    public static boolean testDoubleArrayWithPEASnippet0()  {
        return Arrays.equals(new double[]{0.0d}, new double[]{-0.0d});
    }
}