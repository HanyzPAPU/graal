package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.core.test.GraalCompilerTest;
public class OpaqueNodeTest4 {
    

    public static void opaqueClassSnippet()  {
        /*
         * GR-51558: This would cause an assertion failure in LIR constant load optimization if the
         * opaque is not removed.
         */
        Class<?> c = GraalDirectives.opaque(Object.class);
        if (c.getResource("resource.txt") == null) {
            GraalDirectives.deoptimize();
        }
    }
}