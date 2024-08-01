package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.java.InstanceOfDynamicNode;
public class InstanceOfDynamicTest4 {
    public static Object o = "object";

    public static int isStringIntDynamic()  {
        if (String.class.isInstance(o)) {
            return o.toString().length();
        }
        return o.getClass().getName().length();
    }
}