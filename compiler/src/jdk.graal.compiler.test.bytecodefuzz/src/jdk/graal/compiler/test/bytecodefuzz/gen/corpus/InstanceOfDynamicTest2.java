package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.graal.compiler.nodes.java.InstanceOfDynamicNode;
public class InstanceOfDynamicTest2 {
    public static Object o = Object.class;

    public static boolean isStringDynamic()  {
        return String.class.isInstance(o);
    }
}