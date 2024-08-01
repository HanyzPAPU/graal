package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class HP_control020 {
    public static int count = 60;

    public static int test()  {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            switch (i) {
                case 30:
                    sum += 30;
                    break;
                case 31:
                    sum += 31;
                    break;
                case 32:
                    sum += 32;
                    break;
                case 33:
                    sum += 33;
                    break;
                case 34:
                    sum += 34;
                    break;
                case 35:
                    sum += 35;
                    break;
                case 36:
                    sum += 36;
                    break;
                case 37:
                    sum += 37;
                    break;
                case 38:
                    sum += 38;
                    break;
                case 39:
                    sum += 39;
                    break;
                case 40:
                    sum += 40;
                    break;
                case 41:
                    sum += 41;
                    break;
                case 42:
                    sum += 42;
                    break;
                default:
                    sum += 1;
                    break;
            }
        }
        return sum;
    }
}