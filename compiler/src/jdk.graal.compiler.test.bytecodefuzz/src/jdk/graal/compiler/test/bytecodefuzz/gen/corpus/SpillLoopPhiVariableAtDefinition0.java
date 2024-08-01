package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.jtt.JTTTest;
public class SpillLoopPhiVariableAtDefinition0 {
    public static int arg = 0;

    public static int test()  {
        int count = arg;
        for (int i = 0; i < arg; i++) {
            GraalDirectives.bindToRegister(count);
            GraalDirectives.spillRegisters();
            GraalDirectives.bindToRegister(count);
            if (i == 0) {
                GraalDirectives.spillRegisters();
                continue;
            }
            GraalDirectives.spillRegisters();
            count++;
        }
        return count;
    }
}