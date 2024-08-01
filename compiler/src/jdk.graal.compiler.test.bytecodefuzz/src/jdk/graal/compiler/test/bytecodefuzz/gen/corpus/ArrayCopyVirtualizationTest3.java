package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.java.NewArrayNode;
public class ArrayCopyVirtualizationTest3 {
    

    public int intCopyVirtualization()  {
        int[] array = new int[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 3);
        return array[0];
    }
}