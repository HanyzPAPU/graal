package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;
import jdk.graal.compiler.replacements.test.MethodSubstitutionTest;
import jdk.graal.compiler.nodes.ValueNode;
import jdk.graal.compiler.nodes.graphbuilderconf.GraphBuilderConfiguration;
import jdk.graal.compiler.nodes.graphbuilderconf.GraphBuilderConfiguration.BytecodeExceptionMode;
import jdk.graal.compiler.nodes.graphbuilderconf.GraphBuilderContext;
import jdk.graal.compiler.nodes.graphbuilderconf.InlineInvokePlugin;
import jdk.graal.compiler.nodes.graphbuilderconf.InvocationPlugin;
import jdk.graal.compiler.nodes.graphbuilderconf.InvocationPlugins;
import jdk.graal.compiler.nodes.graphbuilderconf.InvocationPlugins.Registration;
import jdk.graal.compiler.phases.OptimisticOptimizations;
import org.junit.Test;
import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class CheckIndexLongTest7 {
    

    public static long objectsCheckIndexConstant()  {
        return Objects.checkIndex(1L, 2L);
    }
}