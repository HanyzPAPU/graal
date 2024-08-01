package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.java.InstanceOfDynamicNode;
public class InstanceOfDynamicTest13 {
    public static Class<?> c = String.class;
    public static Object o = "object";

    public static int isInstanceIntDynamic()  {
        if (c.isInstance(o)) {
            return o.toString().length();
        }
        return o.getClass().getName().length();
    }
}