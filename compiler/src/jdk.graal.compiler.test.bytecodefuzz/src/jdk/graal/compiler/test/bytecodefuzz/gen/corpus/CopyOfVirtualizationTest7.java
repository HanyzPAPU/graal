package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.util.Arrays;
import org.junit.Test;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.java.NewArrayNode;
public class CopyOfVirtualizationTest7 {
    public static int index = 3;

    public Object objectCopyOfVirtualization()  {
        Object[] array = new Object[]{1, 2, 3, 4};
        return Arrays.copyOf(array, array.length)[index];
    }
}