package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class HP_scope021 {
    public static int count = 22;

    public static int test()  {
        int sum = 0;
        // Although sum is not explicitly read in the tree below it is implicitly read
        // by the guard bail-out.
        for (int i = 0; i < count; i++) {
            if (i > 20) {
                break; // We need to write back either the original value of sum, or the previous
                       // iteration's value.
            }
            sum = i;
        }
        return sum;
    }
}