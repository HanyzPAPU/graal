package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.java.InstanceOfDynamicNode;
public class InstanceOfDynamicTest10 {
    public static Class<?> c = int.class;
    public static Object o = "Object";

    public static boolean isInstanceDynamic()  {
        return c.isInstance(o);
    }
}