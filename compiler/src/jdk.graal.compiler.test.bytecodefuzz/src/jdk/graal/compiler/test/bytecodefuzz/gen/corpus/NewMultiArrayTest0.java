package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.junit.Test;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.ConstantNode;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.ValueNode;
import jdk.graal.compiler.nodes.java.NewMultiArrayNode;
import jdk.graal.compiler.options.OptionValues;
import jdk.vm.ci.code.InstalledCode;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.meta.ResolvedJavaType;
public class NewMultiArrayTest0 {
    

    public static Object newMultiArray()  {
        // This is merely a template - the NewMultiArrayNode is replaced in getCode() above.
        // This also means we need a separate test for correct handling of negative dimensions
        // as deoptimization won't do what we want for a graph modified to be different from the
        // source bytecode.
        return new Object[10][9][8];
    }
}