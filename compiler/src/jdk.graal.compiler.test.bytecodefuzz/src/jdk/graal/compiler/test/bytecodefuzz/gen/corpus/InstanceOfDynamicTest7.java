package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.java.InstanceOfDynamicNode;
public class InstanceOfDynamicTest7 {
    private static Class<?> c = String.class;
    private static Object o = "object";

    public static boolean isInstanceDynamic()  {
        return c.isInstance(o);
    }
}