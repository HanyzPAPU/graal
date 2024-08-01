package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class NestedArithmeticTest0 {
    

    public static int runNestedLoopTry()  {
        int checksum = 0;
        int i3 = 240;
        int i5 = 13485;
        for (int i4 = 303; i4 > 15; i4 -= 2) {
            int f = 1;
            do {
                try {
                    i3 = (38726 / i5);
                    i3 = (i4 % -21500);
                    i5 = (i3 % 787);
                } catch (ArithmeticException a_e) {
                    checksum += f + i3 + i5;
                    return checksum;
                }
                i3 <<= i4;
                i5 <<= i5;
                i3 += (8 + (f * f));
                i5 >>= i5;
                checksum += f;
            } while (++f < 11);
        }
        return checksum;
    }
}