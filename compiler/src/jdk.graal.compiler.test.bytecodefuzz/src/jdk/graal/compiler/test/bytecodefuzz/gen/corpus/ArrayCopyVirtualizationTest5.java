package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.java.NewArrayNode;
public class ArrayCopyVirtualizationTest5 {
    

    public float floatCopyVirtualization()  {
        float[] array = new float[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 3);
        return array[0];
    }
}