package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class OptimizeExtendsTest1 {
    public static char[] value = new char[]{1};
    public static int sumKind = 2;

    public long testSnippet2()  {
        long result = 0;
        char op = value[0];
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
                result += (short) op;
                break;
            case 5:
                result += (byte) op;
                break;
        }
        return result;
    }
}