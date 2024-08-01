package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.java.InstanceOfDynamicNode;
public class InstanceOfDynamicTest1 {
    private static Object o = "object";

    public static boolean isStringDynamic()  {
        return String.class.isInstance(o);
    }
}