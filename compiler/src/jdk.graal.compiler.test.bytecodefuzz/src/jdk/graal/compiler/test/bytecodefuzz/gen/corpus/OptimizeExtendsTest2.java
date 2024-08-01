package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class OptimizeExtendsTest2 {
    public static short[] value = new short[]{1};
    public static int sumKind = 2;

    long testSnippet3()  {
        long result = 0;
        short op = value[0];
        switch (sumKind) {
            case 1:
                result += Long.sum(op, result);
                break;
            case 2:
                result += Integer.sum(op, (int) result);
                break;
            case 3:
                result += Character.toLowerCase(op);
                break;
            case 4:
                result += op;
                break;
            case 5:
                result += (byte) op;
                break;
        }
        return result;
    }
}