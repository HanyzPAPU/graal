package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class System_currentTimeMillis020 {
    

    public static boolean test()  {
        for (int i = 0; i < 5_000_000; i++) {
            long elapsed = System.currentTimeMillis() - System.currentTimeMillis();
            if (elapsed < 100) {
                return true;
            }
        }
        if (!GraalDirectives.inCompiledCode()) {
            // We don't care about the result for the interpreter, C1 or C2
            return true;
        }
        return false;
    }
}