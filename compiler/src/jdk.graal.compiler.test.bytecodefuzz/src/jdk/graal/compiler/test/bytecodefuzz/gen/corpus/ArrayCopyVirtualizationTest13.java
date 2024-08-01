package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.java.NewArrayNode;
public class ArrayCopyVirtualizationTest13 {
    

    public float floatCopyBackwardsVirtualization()  {
        float[] array = new float[]{1, 2, 3, 4};
        System.arraycopy(array, 0, array, 1, 3);
        return array[3];
    }
}