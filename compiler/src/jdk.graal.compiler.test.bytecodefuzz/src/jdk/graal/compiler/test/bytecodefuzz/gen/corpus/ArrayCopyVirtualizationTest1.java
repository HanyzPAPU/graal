package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.java.NewArrayNode;
public class ArrayCopyVirtualizationTest1 {
    

    public short shortCopyVirtualization()  {
        short[] array = new short[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 2);
        return array[0];
    }
}