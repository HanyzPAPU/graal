package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class OptimizeExtendsTest0 {
    public static byte[] value = new byte[]{1};
    public static int sumKind = 2;

    public long testSnippet1()  {
        long result = 0;
        byte op = value[0];
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
        }
        return result;
    }
}