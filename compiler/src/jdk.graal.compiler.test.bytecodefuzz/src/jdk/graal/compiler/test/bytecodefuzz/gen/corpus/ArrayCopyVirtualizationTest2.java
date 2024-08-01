package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.java.NewArrayNode;
public class ArrayCopyVirtualizationTest2 {
    

    public char charCopyVirtualization()  {
        char[] array = new char[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 3);
        return array[0];
    }
}