package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.vm.ci.code.InstalledCode;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import org.junit.Assert;
import org.junit.Test;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.phases.OptimisticOptimizations;
public class DeoptimizeDirectiveTest0 {
    

    public static boolean deoptimizeSnippet()  {
        GraalDirectives.deoptimize();
        return GraalDirectives.inCompiledCode(); // should always return false
    }
}