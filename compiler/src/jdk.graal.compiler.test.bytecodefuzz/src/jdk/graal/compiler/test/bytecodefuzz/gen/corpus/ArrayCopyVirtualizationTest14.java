package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.java.NewArrayNode;
public class ArrayCopyVirtualizationTest14 {
    

    public double doubleCopyBackwardsVirtualization()  {
        double[] array = new double[]{1, 2, 3, 4};
        System.arraycopy(array, 0, array, 1, 3);
        return array[3];
    }
}